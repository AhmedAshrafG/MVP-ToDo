package com.task.ahmedz.xtrava_todo.data.source;

import com.task.ahmedz.xtrava_todo.data.TodoModel;

import io.reactivex.Single;

/**
 * Created by ahmed on 16-Jul-17.
 */

public interface EditTodoDataSource {

	Single<TodoModel> loadTodoItem(String todoId);

}
