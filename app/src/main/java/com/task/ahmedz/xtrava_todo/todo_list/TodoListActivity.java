package com.task.ahmedz.xtrava_todo.todo_list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.task.ahmedz.xtrava_todo.R;
import com.task.ahmedz.xtrava_todo.data.repository.TodoListRepository;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodoListActivity extends AppCompatActivity {
	@BindView(R.id.toolbar)
	Toolbar toolbar;

	private TodoListContract.Presenter mPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo_list);
		ButterKnife.bind(this);

		setSupportActionBar(toolbar);

		TodoListFragment todoListFragment = (TodoListFragment) getSupportFragmentManager()
				.findFragmentById(R.id.todo_list_container);

		if (todoListFragment == null) {
			todoListFragment = TodoListFragment.newInstance();

			getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.todo_list_container, todoListFragment)
					.commit();
		}

		mPresenter = new TodoListPresenter(
				TodoListRepository.getInstance(),
				todoListFragment
		);
	}
}
