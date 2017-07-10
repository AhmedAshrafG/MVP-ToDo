package com.task.ahmedz.xtrava_todo.todo_list;

import android.support.annotation.NonNull;

import com.task.ahmedz.xtrava_todo.base.BasePresenter;
import com.task.ahmedz.xtrava_todo.base.BaseView;
import com.task.ahmedz.xtrava_todo.data.TodoModel;

import java.util.List;

/**
 * Created by ahmed on 09-Jul-17.
 */

public interface TodoListContract {
	interface View extends BaseView<Presenter> {

		void setLoading();

		void setLoaded();

		void showTodoList(List<TodoModel> todoList);

		void addTodoClicked();

		void showTodoMarkedComplete();

		void showLoadingTodoListError();

		void showSuccessfullySavedMessage();
	}

	interface Presenter extends BasePresenter {

		void loadTodoList();

		void addNewTodo();

		void completeTodo(@NonNull TodoModel completedTodo);

		void updateTodo(@NonNull TodoModel updatedTodo, String title);
	}
}
