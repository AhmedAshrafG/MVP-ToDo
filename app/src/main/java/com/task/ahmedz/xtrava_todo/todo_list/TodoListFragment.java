package com.task.ahmedz.xtrava_todo.todo_list;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.task.ahmedz.xtrava_todo.R;
import com.task.ahmedz.xtrava_todo.adapter.TodoRecyclerAdapter;
import com.task.ahmedz.xtrava_todo.base.RefreshFragment;
import com.task.ahmedz.xtrava_todo.callback.TodoInteractionListener;
import com.task.ahmedz.xtrava_todo.data.TodoModel;

import java.util.List;

import butterknife.BindView;
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
	}

	private void setupTodoRecycler() {
		LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
		todoRecyclerAdapter = new TodoRecyclerAdapter(this);
		todoRecyclerView.setLayoutManager(mLinearLayoutManager);
		todoRecyclerView.setAdapter(todoRecyclerAdapter);
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
		mPresenter.subscribe();
	}

	@Override
	public void onPause() {
		super.onPause();
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
		mPresenter.loadTodoList();
	}

	@Override
	public void showTodoList(List<TodoModel> todoList) {
		todoRecyclerAdapter.setTodoItems(todoList);
		setLoaded();
	}

	@Override
	public Observable<AddTodoResult> showAddTodoActivity() {
		return null;
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
}
