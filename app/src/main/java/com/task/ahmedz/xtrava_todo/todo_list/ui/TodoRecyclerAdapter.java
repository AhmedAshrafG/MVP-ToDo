package com.task.ahmedz.xtrava_todo.todo_list.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.task.ahmedz.xtrava_todo.R;
import com.task.ahmedz.xtrava_todo.callback.TodoInteractionListener;
import com.task.ahmedz.xtrava_todo.data.TodoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 10-Jul-17.
 */

public class TodoRecyclerAdapter extends RecyclerView.Adapter<TodoViewHolder> {

	private List<TodoModel> todoItems;
	private TodoInteractionListener todoInteractionListener;
	private int lastPosition = -1;

	public TodoRecyclerAdapter(TodoInteractionListener todoInteractionListener) {
		this.todoInteractionListener = todoInteractionListener;
		this.todoItems = new ArrayList<>();
	}

	public void setTodoItems(List<TodoModel> todoList) {
		this.todoItems = todoList;
		notifyDataSetChanged();
	}

	public List<TodoModel> getTodoItems() {
		return todoItems;
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
		setAnimation(holder.itemView, position);
	}

	private void setAnimation(View viewToAnimate, int position) {
		if (position > lastPosition) {
			Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), android.R.anim.slide_in_left);
			viewToAnimate.startAnimation(animation);
			lastPosition = position;
		}
	}

	@Override
	public int getItemCount() {
		return todoItems.size();
	}
}
