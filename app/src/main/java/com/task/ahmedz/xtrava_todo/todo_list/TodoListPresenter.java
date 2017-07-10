package com.task.ahmedz.xtrava_todo.todo_list;

import android.support.annotation.NonNull;

import com.task.ahmedz.xtrava_todo.data.TodoModel;
import com.task.ahmedz.xtrava_todo.data.remote.ApiRequests;
import com.task.ahmedz.xtrava_todo.data.repository.TodoListRepository;

import java.util.Collections;

import io.reactivex.disposables.CompositeDisposable;

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
		mSubscriptions.add(todoListRepository.getTodoList()
				.doOnSuccess(todoModels -> Collections.sort(todoModels))
				.subscribe(
						mView::showTodoList,
						throwable -> {
							throwable.printStackTrace();
//							mView.showLoadingTodoListError();
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

	}

	@Override
	public void changeTodoCompletionStatus(@NonNull TodoModel todoModel) {
		todoModel.toggleCompleted();
		ApiRequests.updateTodo(todoModel.getId(), todoModel)
				.subscribe();
	}

	@Override
	public void updateTodo(@NonNull TodoModel todoModel) {

	}

	@Override
	public void deleteTodo(@NonNull TodoModel todoModel) {

	}
}
