package com.task.ahmedz.xtrava_todo.todo_list;

import android.support.annotation.NonNull;

import com.task.ahmedz.xtrava_todo.data.TodoModel;
import com.task.ahmedz.xtrava_todo.data.repository.TodoListRepository;

/**
 * Created by ahmed on 10-Jul-17.
 */

class TodoListPresenter implements TodoListContract.Presenter {
	private final TodoListContract.View view;

	public TodoListPresenter(TodoListRepository instance, TodoListContract.View view) {
		this.view = view;
		view.setPresenter(this);
	}

	@Override
	public void subscribe() {

	}

	@Override
	public void unsubscribe() {

	}

	@Override
	public void loadTodoList() {

	}

	@Override
	public void addNewTodo() {

	}

	@Override
	public void completeTodo(@NonNull TodoModel completedTodo) {

	}

	@Override
	public void updateTodo(@NonNull TodoModel updatedTodo, String title) {

	}
}
