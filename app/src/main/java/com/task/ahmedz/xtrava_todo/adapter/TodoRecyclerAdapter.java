package com.task.ahmedz.xtrava_todo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.task.ahmedz.xtrava_todo.R;
import com.task.ahmedz.xtrava_todo.callback.TodoInteractionListener;
import com.task.ahmedz.xtrava_todo.data.TodoModel;
import com.task.ahmedz.xtrava_todo.todo_list.ui.TodoViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 10-Jul-17.
 */

public class TodoRecyclerAdapter extends RecyclerView.Adapter<TodoViewHolder> {

	private final ArrayList<TodoModel> todoItems;
	private TodoInteractionListener todoInteractionListener;

	public TodoRecyclerAdapter(TodoInteractionListener todoInteractionListener) {
		this.todoInteractionListener = todoInteractionListener;
		this.todoItems = new ArrayList<>();
	}

	public void setTodoItems(List<TodoModel> todoList) {
		todoItems.clear();
		todoItems.addAll(todoList);
		notifyDataSetChanged();
	}

	@Override
	public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
		return new TodoViewHolder(view);
	}

	@Override
	public void onBindViewHolder(TodoViewHolder holder, int position) {
		TodoModel todoItem = todoItems.get(position);
		holder.bindViews(todoItem, todoInteractionListener);
	}

	@Override
	public int getItemCount() {
		return todoItems.size();
	}
}
