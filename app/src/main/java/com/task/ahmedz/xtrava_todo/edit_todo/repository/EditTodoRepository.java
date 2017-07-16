package com.task.ahmedz.xtrava_todo.edit_todo.repository;

import com.task.ahmedz.xtrava_todo.data.TodoModel;
import com.task.ahmedz.xtrava_todo.data.local.TodoDatabase;
import com.task.ahmedz.xtrava_todo.data.remote.ApiRequests;

import io.reactivex.Single;

/**
 * Created by ahmed on 10-Jul-17.
 */

public class EditTodoRepository implements EditTodoDataSource {

	@Override
	public Single<TodoModel> loadTodoItem(String todoId) {
		return ApiRequests.getTodoItem(todoId)
				.onErrorResumeNext(throwable ->
						TodoDatabase.getInstance()
								.todoDao()
								.findById(todoId)
								.first(new TodoModel())
				);
	}
}
