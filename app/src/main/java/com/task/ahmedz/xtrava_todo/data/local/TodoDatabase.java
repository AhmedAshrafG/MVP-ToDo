package com.task.ahmedz.xtrava_todo.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.task.ahmedz.xtrava_todo.data.TodoModel;

/**
 * Created by ahmed on 10-Jul-17.
 */

@Database(entities = {TodoModel.class}, version = 1)
public abstract class TodoDatabase extends RoomDatabase {

	private static TodoDatabase instance;
	private static Context appContext;

	public abstract TodoDao todoDao();

	public static TodoDatabase getInstance() {
		if (instance == null)
			instance = Room.databaseBuilder(appContext, TodoDatabase.class, "todo-db").build();

		return instance;
	}

	public static void setAppContext(Context applicationContext) {
		appContext = applicationContext;
	}
}