package com.task.ahmedz.xtrava_todo.data.source;

import android.support.annotation.NonNull;

import com.task.ahmedz.xtrava_todo.data.TodoListData;
import com.task.ahmedz.xtrava_todo.data.TodoModel;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by ahmed on 10-Jul-17.
 */

public interface TodoListDataSource {
	Single<TodoListData> getTodoList();

	Single<TodoModel> updateTodo(@NonNull TodoModel todoModel);

	Single<TodoModel> addTodo(@NonNull TodoModel todoModel);

	Completable deleteTodo(@NonNull String todoId);

	Single<TodoListData> refreshTodoList();
}
