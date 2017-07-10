package com.task.ahmedz.xtrava_todo.todo_list.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.task.ahmedz.xtrava_todo.R;
import com.task.ahmedz.xtrava_todo.callback.TodoInteractionListener;
import com.task.ahmedz.xtrava_todo.data.TodoModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ahmed on 10-Jul-17.
 */

public class TodoViewHolder extends RecyclerView.ViewHolder {
	@BindView(R.id.completed_checkbox)
	CheckBox completedCheckbox;
	@BindView(R.id.title_text_view)
	TextView titleTextView;
	@BindView(R.id.order_text_view)
	TextView orderTextView;
	@BindView(R.id.delete_view)
	View deleteView;

	public TodoViewHolder(View view) {
		super(view);
		ButterKnife.bind(this, view);
	}

	public void bindViews(TodoModel todoItem, TodoInteractionListener todoInteractionListener) {
		completedCheckbox.setChecked(todoItem.isCompleted());
		titleTextView.setText(todoItem.getTitle());
		orderTextView.setText(String.valueOf(todoItem.getOrder()));

		deleteView.setOnClickListener(view -> {
			if (todoInteractionListener != null)
				todoInteractionListener.onDeleteItemClicked(todoItem);
		});

		completedCheckbox.setOnClickListener(view -> {
			if (todoInteractionListener != null)
				todoInteractionListener.onTodoStateChanged(todoItem);
		});

//		completedCheckbox.setOnCheckedChangeListener((compoundButton, b) -> completedCheckbox.setChecked(todoItem.isCompleted()));

		itemView.setOnClickListener(view -> {
			if (todoInteractionListener != null)
				todoInteractionListener.onItemClicked(todoItem);
		});
	}
}
