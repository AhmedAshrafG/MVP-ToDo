package com.task.ahmedz.xtrava_todo;

import android.app.Application;

import com.task.ahmedz.xtrava_todo.data.local.TodoDatabase;

/**
 * Created by ahmed on 10-Jul-17.
 */

public class TodoApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		TodoDatabase.setAppContext(getApplicationContext());
	}
}
