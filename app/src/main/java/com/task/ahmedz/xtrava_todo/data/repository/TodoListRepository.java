package com.task.ahmedz.xtrava_todo.data.repository;

import android.support.annotation.NonNull;

import com.task.ahmedz.xtrava_todo.data.TodoListData;
import com.task.ahmedz.xtrava_todo.data.TodoModel;
import com.task.ahmedz.xtrava_todo.data.local.TodoDao;
import com.task.ahmedz.xtrava_todo.data.local.TodoDatabase;
import com.task.ahmedz.xtrava_todo.data.remote.ApiRequests;
import com.task.ahmedz.xtrava_todo.data.source.TodoListDataSource;

import java.util.ArrayList;

import io.reactivex.Single;
import okhttp3.ResponseBody;

/**
 * Created by ahmed on 10-Jul-17.
 */

public class TodoListRepository implements TodoListDataSource {

	private static TodoListRepository instance;
	private TodoDao todoDao;

	public static TodoListRepository getInstance() {
		if (instance == null)
			instance = new TodoListRepository();

		return instance;
	}

	private TodoListRepository() {
		todoDao = TodoDatabase.getInstance()
				.todoDao();
	}


	@Override
	public Single<TodoListData> getTodoList() {
		return ApiRequests.getTodoList()
				.doOnSuccess(todoListData -> {
					TodoModel[] todoModels = todoListData.getTodoModelsAsArray();
					todoDao.deleteAll(todoModels);
					todoDao.insertAll(todoModels);
				})
				.onErrorResumeNext(throwable -> {
					throwable.printStackTrace();
					return todoDao
							.getAll()
							.first(new ArrayList<>())
							.map(todoModels -> new TodoListData(todoModels, false));
				});
	}

	@Override
	public Single<TodoModel> addTodo(@NonNull TodoModel todoModel) {
		return ApiRequests.addTodo(todoModel)
				.doOnSuccess(backendModel -> {
					todoDao.delete(backendModel);
					todoDao.insert(backendModel);
				});
	}

	@Override
	public Single<ResponseBody> deleteTodo(@NonNull TodoModel todoModel) {
		return ApiRequests.deleteTodo(todoModel.getId())
				.doOnSuccess(responseBody -> todoDao.delete(todoModel));
	}

	@Override
	public Single<TodoListData> refreshTodoList() {
		return ApiRequests.getTodoList()
				.doOnSuccess(todoListData -> {
					TodoModel[] todoModels = todoListData.getTodoModelsAsArray();
					todoDao.deleteAll(todoModels);
					todoDao.insertAll(todoModels);
				});
	}

	@Override
	public Single<TodoModel> updateTodo(TodoModel todoModel) {
		return ApiRequests.updateTodo(todoModel.getId(), todoModel)
				.doOnSuccess(todoDao::update);
	}
}
