package com.task.ahmedz.xtrava_todo.edit_todo;

import com.task.ahmedz.xtrava_todo.base.BasePresenter;
import com.task.ahmedz.xtrava_todo.base.BaseView;

/**
 * Created by ahmed on 16-Jul-17.
 */

public interface EditTodoContract {
	interface View extends BaseView<Presenter> {

		void showTodoDetails(String title, String order);

		void finishWithResult(String title, int order);

		void showTitleError();

		void showOrderError();
	}

	interface Presenter extends BasePresenter {

		void onSubmitClicked(String title, String order);

		void loadTodo(String todoId);

	}
}
