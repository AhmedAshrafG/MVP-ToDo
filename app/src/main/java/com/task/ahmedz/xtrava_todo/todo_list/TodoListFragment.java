package com.task.ahmedz.xtrava_todo.todo_list;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.task.ahmedz.xtrava_todo.R;
import com.task.ahmedz.xtrava_todo.adapter.TodoRecyclerAdapter;
import com.task.ahmedz.xtrava_todo.add_todo.AddTodoActivity;
import com.task.ahmedz.xtrava_todo.base.RefreshFragment;
import com.task.ahmedz.xtrava_todo.callback.TodoInteractionListener;
import com.task.ahmedz.xtrava_todo.data.TodoModel;
import com.task.ahmedz.xtrava_todo.util.RxNavigator;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * Created by ahmed on 10-Jul-17.
 */

public class TodoListFragment extends RefreshFragment implements TodoListContract.View, TodoInteractionListener {

	@BindView(R.id.todo_recycler)
	RecyclerView todoRecyclerView;

	private TodoListContract.Presenter mPresenter;
	private TodoRecyclerAdapter todoRecyclerAdapter;

	public static TodoListFragment newInstance() {
		TodoListFragment todoListFragment = new TodoListFragment();
		return todoListFragment;
	}

	@Override
	protected void onLayoutInflated() {
		super.onLayoutInflated();
		setupTodoRecycler();
		mPresenter.subscribe();
	}

	private void setupTodoRecycler() {
		LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
		todoRecyclerAdapter = new TodoRecyclerAdapter(this);
		todoRecyclerView.setLayoutManager(mLinearLayoutManager);
		todoRecyclerView.setAdapter(todoRecyclerAdapter);
	}

	@OnClick(R.id.fab)
	void onFabClicked() {
		mPresenter.addTodoClicked();
	}

	@Override
	public void onItemClicked(TodoModel todoItem) {
		mPresenter.onTodoClicked(todoItem);
	}

	@Override
	public void onTodoStateChanged(TodoModel todoModel) {
		mPresenter.onTodoStateChanged(todoModel);
	}

	@Override
	public void setPresenter(TodoListContract.Presenter mPresenter) {
		if (mPresenter == null)
			throw new IllegalArgumentException("Presenter can't be null!");
		this.mPresenter = mPresenter;
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mPresenter.unsubscribe();
	}

	@Override
	protected int getLayoutResId() {
		return R.layout.fragment_todo_list;
	}

	@Override
	public void showLoadingView() {
		setLoading();
	}

	@Override
	public void hideLoadingView() {
		setLoaded();
	}

	@Override
	protected void onRefresh() {
		mPresenter.refreshTodoList();
	}

	@Override
	public void showTodoList(List<TodoModel> todoList) {
		todoRecyclerAdapter.setTodoItems(todoList);
	}

	@Override
	public Observable<AddTodoResult> showAddTodoActivity() {
		Intent intent = new Intent(getActivity(), AddTodoActivity.class);
		return RxNavigator
				.navigateAndWait(this, intent)
				.map(result -> {
					Intent data = result.data();
					String todoTitle = data.getStringExtra(getString(R.string.todo_title));
					int todoOrder = data.getIntExtra(getString(R.string.todo_order), 1);
					return new AddTodoResult(todoTitle, todoOrder);
				});
	}

	@Override
	public void showTodoMarkedComplete() {

	}

	@Override
	public void showLoadingTodoListError() {
		setLoaded(R.string.error_message);
	}

	@Override
	public void showSuccessfullySavedMessage() {

	}

	@Override
	public void showEditTodoActivity() {

	}

	@Override
	public void refreshTodoState(TodoModel todoModel) {
		List<TodoModel> todoItems = todoRecyclerAdapter.getTodoItems();
		todoItems.remove(todoModel);
		todoItems.add(todoModel);
		Collections.sort(todoItems);
		todoRecyclerAdapter.setTodoItems(todoItems);
	}

	@Override
	public void showConnectionError() {
		showSnackBar(R.string.connection_error);
	}

	@Override
	public void showUpdateError() {
		showSnackBar(R.string.update_error);
	}

	@Override
	public void addTodoModel(TodoModel todoModel) {
		List<TodoModel> todoItems = todoRecyclerAdapter.getTodoItems();
		todoItems.add(todoModel);
		Collections.sort(todoItems);
		todoRecyclerAdapter.setTodoItems(todoItems);
	}

	@Override
	public void showAddTodoError() {
		showSnackBar(R.string.error_message);
	}
}
