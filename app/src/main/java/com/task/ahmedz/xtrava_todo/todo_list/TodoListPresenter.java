package com.task.ahmedz.xtrava_todo.todo_list;

import android.support.annotation.NonNull;

import com.task.ahmedz.xtrava_todo.data.TodoListData;
import com.task.ahmedz.xtrava_todo.data.TodoModel;
import com.task.ahmedz.xtrava_todo.data.source.TodoListDataSource;
import com.task.ahmedz.xtrava_todo.util.DeleteRequestResponseParser;
import com.task.ahmedz.xtrava_todo.util.Utils;

import java.util.Collections;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ahmed on 10-Jul-17.
 */

class TodoListPresenter implements TodoListContract.Presenter {

	private final TodoListContract.View mView;
	private final TodoListDataSource mRepository;
	private final CompositeDisposable mSubscriptions;

	public TodoListPresenter(TodoListDataSource mRepository, TodoListContract.View mView) {
		if (mView == null)
			throw new IllegalArgumentException("View can't be null!");

		this.mView = mView;
		this.mRepository = mRepository;
		this.mSubscriptions = new CompositeDisposable();

		mView.setPresenter(this);
	}

	@Override
	public void subscribe() {
		loadTodoList();
	}

	@Override
	public void unsubscribe() {
		mSubscriptions.dispose();
	}

	@Override
	public void loadTodoList() {
		mSubscriptions.add(
				mRepository.getTodoList()
						.doOnSuccess(todoListData -> {
							if (!todoListData.isRemote()) mView.showConnectionError();
						})
						.map(TodoListData::getTodoModels)
						.doOnSuccess(todoModels -> Collections.sort(todoModels))
						.subscribeOn(Schedulers.newThread())
						.observeOn(AndroidSchedulers.mainThread())
						.doOnSubscribe(disposable -> mView.showLoadingView())
						.subscribe(
								(todoList) -> {
									mView.hideLoadingView();
									if (todoList.isEmpty())
										mView.showEmptyTodoMessage();
									else
										mView.showTodoList(todoList);
								},
								(throwable) -> {
									throwable.printStackTrace();
									mView.hideLoadingView();
								}
						)
		);
	}

	@Override
	public void refreshTodoList() {
		mSubscriptions.add(
				mRepository.refreshTodoList()
						.map(TodoListData::getTodoModels)
						.doOnSuccess(Collections::sort)
						.subscribeOn(Schedulers.newThread())
						.observeOn(AndroidSchedulers.mainThread())
						.doFinally(() -> mView.hideLoadingView())
						.subscribe(
								(todoList) -> {
									if (todoList.isEmpty())
										mView.showEmptyTodoMessage();
									else
										mView.showTodoList(todoList);
								},
								throwable -> {
									throwable.printStackTrace();
									mView.showConnectionError();
								}
						)
		);
	}

	@Override
	public void addTodoClicked() {
		mView.showAddTodoActivity()
				.flatMapSingle(addTodoResult -> {
					String title = addTodoResult.getTitle();
					int order = addTodoResult.getOrder();

					return mRepository.addTodo(new TodoModel(title, order))
							.subscribeOn(Schedulers.newThread())
							.observeOn(AndroidSchedulers.mainThread());
				})
				.doOnSubscribe(disposable -> mView.showLoadingView())
				.doFinally(() -> mView.hideLoadingView())
				.subscribe(
						(todoModel) -> {
							mView.addTodo(todoModel);
							mView.showSuccessfullySavedMessage();
						},
						throwable -> {
							throwable.printStackTrace();
							mView.showAddTodoError();
						}
				);
	}

	@Override
	public void onTodoClicked(TodoModel todoItem) {
		mView.showEditTodoActivity(todoItem.getId())
				.flatMapSingle(editTodoResult -> {
					String title = editTodoResult.getTitle();
					int order = editTodoResult.getOrder();

					TodoModel clone = Utils.deepClone(todoItem);
					clone.setTitle(title);
					clone.setOrder(order);

					return mRepository.updateTodo(clone)
							.subscribeOn(Schedulers.newThread())
							.observeOn(AndroidSchedulers.mainThread());
				})
				.doOnSubscribe(disposable -> mView.showLoadingView())
				.doFinally(() -> mView.hideLoadingView())
				.subscribe(
						(todoModel) -> {
							mView.updateTodo(todoModel);
							mView.showSuccessfullySavedMessage();
						},
						throwable -> {
							throwable.printStackTrace();
							mView.showAddTodoError();
						}
				);
	}

	@Override
	public void onTodoStateChanged(TodoModel todoItem) {
		TodoModel todoModel = Utils.deepClone(todoItem);
		todoModel.toggleCompleted();
		updateTodo(todoModel);
	}

	@Override
	public void onTodoDeleteClicked(TodoModel todoModel) {
		mView.showDeleteConfirmationDialog(todoModel);
	}

	@Override
	public void updateTodo(@NonNull TodoModel todoModel) {
		mSubscriptions.add(
				mRepository.updateTodo(todoModel)
						.subscribeOn(Schedulers.newThread())
						.observeOn(AndroidSchedulers.mainThread())
						.doFinally(() -> mView.hideLoadingView())
						.subscribe(
								mView::updateTodo,
								throwable -> {
									throwable.printStackTrace();
									mView.showUpdateError();
									todoModel.toggleCompleted();
									mView.updateTodo(todoModel);
								}
						)
		);
	}

	@Override
	public void onDeleteConfirmed(@NonNull TodoModel todoModel) {
		mSubscriptions.add(
				mRepository.deleteTodo(todoModel)
						.map(responseBody -> new DeleteRequestResponseParser(responseBody))
						.flatMapCompletable(responseParser -> {
							if (responseParser.isSuccess())
								return Completable.complete();
							else
								return Completable.error(new Exception());
						})
						.subscribeOn(Schedulers.newThread())
						.observeOn(AndroidSchedulers.mainThread())
						.doFinally(() -> mView.hideLoadingView())
						.subscribe(
								() -> mView.removeTodo(todoModel),
								throwable -> {
									throwable.printStackTrace();
									mView.showDeleteError();
									mView.updateTodo(todoModel);
								}
						)
		);
	}
}
