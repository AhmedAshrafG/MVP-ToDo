package com.task.ahmedz.xtrava_todo.todo_list;

import android.support.annotation.NonNull;

import com.task.ahmedz.xtrava_todo.data.TodoListData;
import com.task.ahmedz.xtrava_todo.data.TodoModel;
import com.task.ahmedz.xtrava_todo.data.remote.ApiRequests;
import com.task.ahmedz.xtrava_todo.data.repository.TodoListRepository;

import java.util.Collections;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ahmed on 10-Jul-17.
 */

class TodoListPresenter implements TodoListContract.Presenter {

	private final TodoListContract.View mView;
	private final TodoListRepository todoListRepository;
	private final CompositeDisposable mSubscriptions;

	public TodoListPresenter(TodoListRepository todoListRepository, TodoListContract.View mView) {
		if (mView == null)
			throw new IllegalArgumentException("View can't be null!");
		this.mView = mView;
		this.todoListRepository = todoListRepository;
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
				todoListRepository.getTodoList()
						.doOnSuccess(todoListData -> {
							if (!todoListData.isRemote()) mView.showConnectionError();
						})
						.map(TodoListData::getTodoModels)
						.doOnSuccess(todoModels -> Collections.sort(todoModels))
						.subscribeOn(Schedulers.newThread())
						.observeOn(AndroidSchedulers.mainThread())
						.subscribe(
								mView::showTodoList,
								throwable -> {
									throwable.printStackTrace();
									mView.showLoadingTodoListError();
								}
						)
		);
	}

	@Override
	public void addTodoClicked() {
		mView.showAddTodoActivity();
	}

	@Override
	public void onTodoClicked(TodoModel todoItem) {
		mView.showEditTodoActivity();
	}

	@Override
	public void onTodoStateChanged(TodoModel todoItem) {
		mSubscriptions.add(
				todoListRepository.updateTodo(todoItem)
						.subscribeOn(Schedulers.newThread())
						.observeOn(AndroidSchedulers.mainThread())
						.subscribe(
								todoModel -> mView.updateTodo(),
								throwable -> {
									throwable.printStackTrace();
									mView.showUpdateError();
								}
						)
		);
	}

	@Override
	public void refreshTodoList() {
		mSubscriptions.add(
				todoListRepository.refreshTodoList()
						.map(TodoListData::getTodoModels)
						.doOnSuccess(todoModels -> Collections.sort(todoModels))
						.subscribeOn(Schedulers.newThread())
						.observeOn(AndroidSchedulers.mainThread())
						.subscribe(
								mView::showTodoList,
								throwable -> {
									throwable.printStackTrace();
									mView.showConnectionError();
									mView.hideLoadingView();
								}
						)
		);
	}

	@Override
	public void changeTodoCompletionStatus(@NonNull TodoModel todoModel) {
		todoModel.toggleCompleted();
		updateTodo(todoModel);
	}

	@Override
	public void updateTodo(@NonNull TodoModel todoModel) {
		mSubscriptions.add(
				ApiRequests.updateTodo(todoModel.getId(), todoModel)
						.subscribeOn(Schedulers.newThread())
						.observeOn(AndroidSchedulers.mainThread())
						.subscribe()
		);
	}

	@Override
	public void deleteTodo(@NonNull TodoModel todoModel) {

	}
}
