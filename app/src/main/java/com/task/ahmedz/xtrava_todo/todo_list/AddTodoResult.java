package com.task.ahmedz.xtrava_todo.todo_list;

/**
 * Created by ahmed on 10-Jul-17.
 */

public class AddTodoResult {
	String title;
	int order;

	public AddTodoResult(String title, int order) {
		this.title = title;
		this.order = order;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
}
