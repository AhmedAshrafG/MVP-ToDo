package com.task.ahmedz.xtrava_todo.todo_list;

import android.support.annotation.NonNull;

import com.task.ahmedz.xtrava_todo.base.BasePresenter;
import com.task.ahmedz.xtrava_todo.base.BaseView;
import com.task.ahmedz.xtrava_todo.data.TodoModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ahmed on 09-Jul-17.
 */

public interface TodoListContract {
	interface View extends BaseView<Presenter> {

		void showLoadingView();

		void hideLoadingView();

		void showTodoList(List<TodoModel> todoList);

		Observable<TodoActivityResult> showAddTodoActivity();

		Observable<TodoActivityResult> showEditTodoActivity(String todoId);

		void addTodo(TodoModel todoModel);

		void updateTodo(TodoModel newModel);

		void removeTodo(TodoModel todoModel);

		void showDeleteConfirmationDialog(TodoModel todoModel);

		void showSuccessfullySavedMessage();

		void showConnectionError();

		void showUpdateError();

		void showDeleteError();

		void showAddTodoError();

		void showEmptyTodoMessage();
	}

	interface Presenter extends BasePresenter {

		void loadTodoList();

		void refreshTodoList();

		void addTodoClicked();

		void updateTodo(@NonNull TodoModel todoModel);

		void onDeleteConfirmed(@NonNull TodoModel todoModel);

		void onTodoClicked(TodoModel todoItem);

		void onTodoStateChanged(TodoModel todoItem);

		void onTodoDeleteClicked(TodoModel todoItem);
	}
}
