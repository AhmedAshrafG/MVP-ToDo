package com.task.ahmedz.xtrava_todo.todo_list;

import com.task.ahmedz.xtrava_todo.R;
import com.task.ahmedz.xtrava_todo.base.RefreshFragment;
import com.task.ahmedz.xtrava_todo.data.TodoModel;

import java.util.List;

/**
 * Created by ahmed on 10-Jul-17.
 */

public class TodoListFragment extends RefreshFragment implements TodoListContract.View {

	private TodoListContract.Presenter mPresenter;

	public static TodoListFragment newInstance() {
		TodoListFragment todoListFragment = new TodoListFragment();
		return todoListFragment;
	}

	@Override
	public void setPresenter(TodoListContract.Presenter presenter) {
		this.mPresenter = presenter;
	}

	@Override
	protected int getLayoutResId() {
		return R.layout.fragment_todo_list;
	}

	@Override
	public void setLoading() {

	}

	@Override
	public void setLoaded() {

	}

	@Override
	protected void onRefresh() {

	}

	@Override
	public void showTodoList(List<TodoModel> todoList) {

	}

	@Override
	public void addTodoClicked() {

	}

	@Override
	public void showTodoMarkedComplete() {

	}

	@Override
	public void showLoadingTodoListError() {

	}

	@Override
	public void showSuccessfullySavedMessage() {

	}
}
