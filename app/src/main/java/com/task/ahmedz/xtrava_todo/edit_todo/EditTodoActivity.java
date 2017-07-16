package com.task.ahmedz.xtrava_todo.edit_todo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.task.ahmedz.xtrava_todo.R;
import com.task.ahmedz.xtrava_todo.edit_todo.repository.EditTodoRepository;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ahmed on 10-Jul-17.
 */

public class EditTodoActivity extends AppCompatActivity {
	@BindView(R.id.toolbar)
	Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_todo_activity);
		ButterKnife.bind(this);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		String todoId = getIntent().getStringExtra(getString(R.string.todo_id));

		EditTodoFragment editTodoFragment = (EditTodoFragment) getSupportFragmentManager()
				.findFragmentById(R.id.edit_todo_container);

		if (editTodoFragment == null) {
			editTodoFragment = EditTodoFragment.newInstance();

			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.edit_todo_container, editTodoFragment)
					.commit();
		}

		new EditTodoPresenter(
				editTodoFragment,
				new EditTodoRepository(),
				todoId
		);
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
