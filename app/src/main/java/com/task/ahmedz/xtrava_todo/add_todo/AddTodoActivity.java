package com.task.ahmedz.xtrava_todo.add_todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.EditText;

import com.task.ahmedz.xtrava_todo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddTodoActivity extends AppCompatActivity {

	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.title_text_view)
	EditText titleTextView;
	@BindView(R.id.order_text_view)
	EditText orderTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_todo_activity);
		ButterKnife.bind(this);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@OnClick(R.id.fab)
	void onFabClicked() {
		String title = titleTextView.getText().toString();
		String order = orderTextView.getText().toString();

		if (TextUtils.isEmpty(title)) {
			titleTextView.setError(getString(R.string.input_missing_error));
			return;
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
