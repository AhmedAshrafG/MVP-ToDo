package com.task.ahmedz.xtrava_todo.callback;

import com.task.ahmedz.xtrava_todo.data.TodoModel;

/**
 * Created by ahmed on 10-Jul-17.
 */

public interface TodoInteractionListener {

	void onItemClicked(TodoModel todoItem);

	void onTodoStateChanged(TodoModel todoItem);

	void onDeleteItemClicked(TodoModel todoItem);
}
