package com.task.ahmedz.xtrava_todo.todo_list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.task.ahmedz.xtrava_todo.R;
import com.task.ahmedz.xtrava_todo.data.repository.TodoListRepository;

public class TodoListActivity extends AppCompatActivity {

	private TodoListContract.Presenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo_list);

		TodoListFragment todoListFragment = (TodoListFragment) getSupportFragmentManager()
				.findFragmentById(R.id.todo_list_container);

		if (todoListFragment == null) {
			todoListFragment = TodoListFragment.newInstance();

			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.todo_list_container, todoListFragment)
					.commit();
		}

		presenter = new TodoListPresenter(
				TodoListRepository.getInstance(),
				todoListFragment
		);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_todo_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
