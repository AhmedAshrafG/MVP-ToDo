package com.task.ahmedz.xtrava_todo.edit_todo.repository;

import com.task.ahmedz.xtrava_todo.data.TodoModel;
import com.task.ahmedz.xtrava_todo.data.local.TodoDatabase;

import io.reactivex.Flowable;

/**
 * Created by ahmed on 10-Jul-17.
 */

public class EditTodoRepository {
	private String todoId;

	private EditTodoRepository(String todoId) {
		this.todoId = todoId;
	}

	public static EditTodoRepository getInstance(String todoId) {
		return new EditTodoRepository(todoId);
	}

	public Flowable<TodoModel> loadTodoItem() {
		return TodoDatabase.getInstance()
				.todoDao()
				.findById(todoId);
	}
}
