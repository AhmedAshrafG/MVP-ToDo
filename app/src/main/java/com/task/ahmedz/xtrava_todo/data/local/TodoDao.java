package com.task.ahmedz.xtrava_todo.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.task.ahmedz.xtrava_todo.data.TodoModel;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by ahmed on 10-Jul-17.
 */

@Dao
public interface TodoDao {
	@Query("SELECT * FROM todo")
	Flowable<List<TodoModel>> getAll();

	@Query("SELECT * FROM todo WHERE id = :todoId")
	Flowable<TodoModel> findById(String todoId);

	@Update
	void update(TodoModel todoModel);

	@Insert
	void insert(TodoModel todoModel);

	@Insert
	void insertAll(TodoModel...todoModels);

	@Delete
	void deleteAll(TodoModel...todoModels);
}
