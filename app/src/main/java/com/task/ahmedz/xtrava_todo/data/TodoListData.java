package com.task.ahmedz.xtrava_todo.data;

import java.util.List;

/**
 * Created by ahmed on 10-Jul-17.
 */

public class TodoListData {
	List<TodoModel> todoModels;
	boolean isRemote;

	public TodoListData(List<TodoModel> todoModels, boolean isRemote) {
		this.todoModels = todoModels;
		this.isRemote = isRemote;
	}

	public List<TodoModel> getTodoModels() {
		return todoModels;
	}

	public TodoModel[] getTodoModelsAsArray() {
		return todoModels.toArray(new TodoModel[todoModels.size()]);
	}

	public void setTodoModels(List<TodoModel> todoModels) {
		this.todoModels = todoModels;
	}

	public boolean isRemote() {
		return isRemote;
	}

	public void setRemote(boolean remote) {
		isRemote = remote;
	}
}
