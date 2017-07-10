package com.task.ahmedz.xtrava_todo.data.repository;

import android.support.annotation.NonNull;

import com.task.ahmedz.xtrava_todo.data.TodoModel;
import com.task.ahmedz.xtrava_todo.data.remote.ApiRequests;
import com.task.ahmedz.xtrava_todo.data.source.TodoListDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

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
	public Single<List<TodoModel>> getTodoList() {
		return ApiRequests.getTodoList()
				.onErrorResumeNext(throwable -> {
					return ApiRequests.getTodoList();
				});
	}

	@Override
	public Single<TodoModel> updateTodo(@NonNull TodoModel todoModel) {
		return null;
	}

	@Override
	public Single<TodoModel> addTodo(@NonNull TodoModel todoModel) {
		return null;
	}

	@Override
	public Completable deleteTodo(@NonNull String todoId) {
		return null;
	}

	@Override
	public void refreshTodoList() {

	}
}
