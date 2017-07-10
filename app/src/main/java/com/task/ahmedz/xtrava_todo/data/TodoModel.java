package com.task.ahmedz.xtrava_todo.data;

import android.support.annotation.NonNull;

/**
 * Created by ahmed on 09-Jul-17.
 */

public class TodoModel implements Comparable<TodoModel> {
	boolean completed;
	String title;
	int order;
	String url;
	String id;

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void toggleCompleted() {
		completed = !completed;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof TodoModel))
			return false;

		TodoModel that = (TodoModel) obj;
		return this.getId() == that.getId();
	}

	@Override
	public int compareTo(@NonNull TodoModel that) {
		return that.getOrder() - this.getOrder();
	}
}
