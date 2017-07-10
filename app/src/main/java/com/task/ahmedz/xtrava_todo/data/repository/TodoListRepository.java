package com.task.ahmedz.xtrava_todo.data.repository;

import android.support.annotation.NonNull;

import com.task.ahmedz.xtrava_todo.data.TodoModel;
import com.task.ahmedz.xtrava_todo.data.source.TodoListDataSource;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ahmed on 10-Jul-17.
 */

public class TodoListRepository implements TodoListDataSource {

	private static TodoListRepository instance;

	public static TodoListRepository getInstance() {
		if (instance == null)
			instance = new TodoListRepository();

		return instance;
	}

	@Override
	public Observable<List<TodoModel>> getTodoList() {
		return null;
	}

	@Override
	public void saveTodo(@NonNull TodoModel task) {

	}

	@Override
	public void completeTodo(@NonNull TodoModel task) {

	}

	@Override
	public void completeTodo(@NonNull String taskId) {

	}

	@Override
	public void refreshTodos() {

	}

	@Override
	public void deleteTodo(@NonNull String taskId) {

	}
}
