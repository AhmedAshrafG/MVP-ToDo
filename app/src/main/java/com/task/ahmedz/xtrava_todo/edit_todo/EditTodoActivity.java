package com.task.ahmedz.xtrava_todo.edit_todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.EditText;

import com.task.ahmedz.xtrava_todo.R;
import com.task.ahmedz.xtrava_todo.data.TodoModel;
import com.task.ahmedz.xtrava_todo.edit_todo.repository.EditTodoRepository;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ahmed on 10-Jul-17.
 */

public class EditTodoActivity extends AppCompatActivity {
	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.title_text_view)
	EditText titleTextView;
	@BindView(R.id.order_text_view)
	EditText orderTextView;
	private EditTodoRepository mRepository;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.todo_details_layout);
		ButterKnife.bind(this);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		prepareData();
	}

	private void prepareData() {
		String todoId = getIntent().getStringExtra(getString(R.string.todo_id));
		mRepository = EditTodoRepository.getInstance(todoId);
		mRepository.loadTodoItem()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(this::bindUI, Throwable::printStackTrace);
	}

	private void bindUI(TodoModel todoModel) {
		orderTextView.setText(String.valueOf(todoModel.getOrder()));
		titleTextView.setText(todoModel.getTitle());
	}

	@OnClick(R.id.fab)
	void onFabClicked() {
		String title = titleTextView.getText().toString();
		String order = orderTextView.getText().toString();

		if (TextUtils.isEmpty(title)) {
			titleTextView.setError(getString(R.string.input_missing_error));
		}
		if (TextUtils.isEmpty(order)) {
			orderTextView.setError(getString(R.string.input_missing_error));
			return;
		}

		Intent intent = new Intent();
		intent.putExtra(getString(R.string.todo_title), title);
		intent.putExtra(getString(R.string.todo_order), Integer.valueOf(order));
		setResult(RESULT_OK, intent);
		finish();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
