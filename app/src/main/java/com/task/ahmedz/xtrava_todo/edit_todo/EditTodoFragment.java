package com.task.ahmedz.xtrava_todo.edit_todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.task.ahmedz.xtrava_todo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by ahmed on 16-Jul-17.
 */

public class EditTodoFragment extends Fragment implements EditTodoContract.View {

	@BindView(R.id.title_text_view)
	EditText titleTextView;
	@BindView(R.id.order_text_view)
	EditText orderTextView;

	private EditTodoContract.Presenter mPresenter;

	public static EditTodoFragment newInstance() {
		EditTodoFragment editTodoFragment = new EditTodoFragment();
		return editTodoFragment;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_edit_todo, container, false);
		ButterKnife.bind(this, view);
		return view;
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
	public void setPresenter(EditTodoContract.Presenter presenter) {
		this.mPresenter = presenter;
	}

	@OnClick(R.id.fab)
	void onFabClicked() {
		String title = titleTextView.getText().toString();
		String order = orderTextView.getText().toString();

		mPresenter.onSubmitClicked(title, order);
	}

	@Override
	public void showTodoDetails(String title, String order) {
		orderTextView.setText(order);
		titleTextView.setText(title);
	}

	@Override
	public void finishWithResult(String title, int order) {
		Intent intent = new Intent();
		intent.putExtra(getString(R.string.todo_title), title);
		intent.putExtra(getString(R.string.todo_order), order);
		getActivity().setResult(RESULT_OK, intent);
		getActivity().finish();
	}

	@Override
	public void showTitleError() {
		titleTextView.setError(getString(R.string.input_missing_error));
	}

	@Override
	public void showOrderError() {
		orderTextView.setError(getString(R.string.input_missing_error));
	}
}
