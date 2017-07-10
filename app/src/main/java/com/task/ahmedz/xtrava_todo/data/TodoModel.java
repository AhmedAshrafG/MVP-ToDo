package com.task.ahmedz.xtrava_todo.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by ahmed on 09-Jul-17.
 */

@Entity(tableName = "todo")
public class TodoModel implements Comparable<TodoModel> {
	@PrimaryKey
	private String id;
	private boolean completed;
	private String title;
	private int order;
	private String url;

	public TodoModel(String title, int order) {
		this.title = title;
		this.order = order;
	}

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
		return this.getId().equals(that.getId());
	}

	@Override
	public int compareTo(@NonNull TodoModel that) {
		int orderDif = that.getOrder() - this.getOrder();
		if (orderDif != 0)
			return orderDif;
		else
			return that.getId().compareTo(this.getId());
	}
}
