package com.task.ahmedz.xtrava_todo.edit_todo.repository;

import com.task.ahmedz.xtrava_todo.data.TodoModel;

import io.reactivex.Single;

/**
 * Created by ahmed on 16-Jul-17.
 */

interface EditTodoDataSource {

	Single<TodoModel> loadTodoItem(String todoId);

}
