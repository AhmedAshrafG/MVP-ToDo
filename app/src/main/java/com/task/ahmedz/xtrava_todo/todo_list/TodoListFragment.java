package com.task.ahmedz.xtrava_todo.todo_list;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.task.ahmedz.xtrava_todo.R;
import com.task.ahmedz.xtrava_todo.add_todo.AddTodoActivity;
import com.task.ahmedz.xtrava_todo.base.RefreshFragment;
import com.task.ahmedz.xtrava_todo.callback.TodoInteractionListener;
import com.task.ahmedz.xtrava_todo.data.TodoModel;
import com.task.ahmedz.xtrava_todo.edit_todo.EditTodoActivity;
import com.task.ahmedz.xtrava_todo.todo_list.ui.TodoRecyclerAdapter;
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
	@BindView(R.id.empty_view)
	LinearLayout emptyView;
	@BindView(R.id.fab)
	FloatingActionButton fab;

	private TodoListContract.Presenter mPresenter;
	private TodoRecyclerAdapter todoRecyclerAdapter;

	public static TodoListFragment newInstance() {
		TodoListFragment todoListFragment = new TodoListFragment();
		return todoListFragment;
	}

	@Override
	protected int getLayoutResId() {
		return R.layout.fragment_todo_list;
	}

	@Override
	protected void onLayoutInflated() {
		setupTodoRecycler();
		mPresenter.subscribe();
	}

	private void setupTodoRecycler() {
		LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
		todoRecyclerAdapter = new TodoRecyclerAdapter(this);
		todoRecyclerView.setLayoutManager(mLinearLayoutManager);
		todoRecyclerView.setAdapter(todoRecyclerAdapter);

		todoRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				if (dy > 0)
					fab.hide();
				else if (dy < 0)
					fab.show();
			}
		});
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
	public void onTodoStateChanged(TodoModel todoItem) {
		mPresenter.onTodoStateChanged(todoItem);
	}

	@Override
	public void onDeleteItemClicked(TodoModel todoItem) {
		mPresenter.onTodoDeleteClicked(todoItem);
	}

	@Override
	public void setPresenter(TodoListContract.Presenter mPresenter) {
		if (mPresenter == null)
			throw new IllegalArgumentException("Presenter can't be null!");
		this.mPresenter = mPresenter;
	}

	@Override
	public void showLoadingView() {
		setLoading();
	}

	@Override
	public void hideLoadingView() {
		emptyView.setVisibility(View.GONE);
		setLoaded();
	}

	@Override
	protected void onRefresh() {
		mPresenter.refreshTodoList();
	}

	@Override
	public void showTodoList(List<TodoModel> todoList) {
		emptyView.setVisibility(View.GONE);
		todoRecyclerAdapter.setTodoItems(todoList);
	}

	@Override
	public Observable<TodoActivityResult> showAddTodoActivity() {
		Intent intent = new Intent(getActivity(), AddTodoActivity.class);
		return RxNavigator
				.navigateAndWait(this, intent)
				.map(result -> {
					Intent data = result.data();
					String todoTitle = data.getStringExtra(getString(R.string.todo_title));
					int todoOrder = data.getIntExtra(getString(R.string.todo_order), 1);
					return new TodoActivityResult(todoTitle, todoOrder);
				});
	}

	@Override
	public Observable<TodoActivityResult> showEditTodoActivity(String todoId) {
		Intent intent = new Intent(getActivity(), EditTodoActivity.class);
		intent.putExtra(getString(R.string.todo_id), todoId);

		return RxNavigator
				.navigateAndWait(this, intent)
				.map(result -> {
					Intent data = result.data();
					String todoTitle = data.getStringExtra(getString(R.string.todo_title));
					int todoOrder = data.getIntExtra(getString(R.string.todo_order), 1);
					return new TodoActivityResult(todoTitle, todoOrder);
				});
	}

	@Override
	public void updateTodo(TodoModel todoModel) {
		List<TodoModel> todoItems = todoRecyclerAdapter.getTodoItems();
		todoItems.remove(todoModel);
		todoItems.add(todoModel);
		Collections.sort(todoItems);
		todoRecyclerAdapter.setTodoItems(todoItems);
	}

	@Override
	public void removeTodo(TodoModel todoModel) {
		List<TodoModel> todoItems = todoRecyclerAdapter.getTodoItems();
		todoItems.remove(todoModel);
		Collections.sort(todoItems);
		todoRecyclerAdapter.setTodoItems(todoItems);
	}

	@Override
	public void addTodo(TodoModel todoModel) {
		List<TodoModel> todoItems = todoRecyclerAdapter.getTodoItems();
		todoItems.add(todoModel);
		Collections.sort(todoItems);
		todoRecyclerAdapter.setTodoItems(todoItems);
	}

	@Override
	public void showSuccessfullySavedMessage() {
		showSnackBar(R.string.success_message);
	}

	@Override
	public void showConnectionError() {
		showSnackBar(R.string.offline_mode_error);
	}

	@Override
	public void showUpdateError() {
		showSnackBar(R.string.connection_error);
	}

	@Override
	public void showDeleteError() {
		showSnackBar(R.string.connection_error);
	}

	@Override
	public void showAddTodoError() {
		showSnackBar(R.string.error_message);
	}

	@Override
	public void showEmptyTodoMessage() {
		emptyView.setVisibility(View.VISIBLE);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mPresenter.unsubscribe();
	}
}
