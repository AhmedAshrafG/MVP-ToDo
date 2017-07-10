package com.task.ahmedz.xtrava_todo.data.source;

import android.support.annotation.NonNull;

import com.task.ahmedz.xtrava_todo.data.TodoModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ahmed on 10-Jul-17.
 */

public interface TodoListDataSource {
	Observable<List<TodoModel>> getTodoList();

	void saveTodo(@NonNull TodoModel task);

	void completeTodo(@NonNull TodoModel task);

	void completeTodo(@NonNull String taskId);

	void refreshTodos();

	void deleteTodo(@NonNull String taskId);
}
