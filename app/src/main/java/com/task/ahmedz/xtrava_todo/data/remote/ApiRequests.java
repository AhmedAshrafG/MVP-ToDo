package com.task.ahmedz.xtrava_todo.data.remote;

import com.google.gson.Gson;
import com.task.ahmedz.xtrava_todo.data.TodoListData;
import com.task.ahmedz.xtrava_todo.data.TodoModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by ahmed on 10-Jul-17.
 */

public class ApiRequests {

	private static final long TIMEOUT = 5;
	private static final long SHORT_TIMEOUT = 2;

	public static Single<TodoListData> getTodoList() {
		return ApiClient.getClient()
				.create(ApiInterface.class)
				.getTodoList()
				.map(todoModels -> new TodoListData(todoModels, true))
				.timeout(TIMEOUT, TimeUnit.SECONDS);
	}

	public static Single<TodoModel> getTodoItem(String todoId) {
		return ApiClient.getClient()
				.create(ApiInterface.class)
				.getTodo(todoId)
				.timeout(SHORT_TIMEOUT, TimeUnit.SECONDS);
	}

	public static Single<TodoModel> updateTodo(String todoId, TodoModel todoModel) {
		return ApiClient.getClient()
				.create(ApiInterface.class)
				.updateTodo(todoId, getRequestBody(todoModel))
				.timeout(TIMEOUT, TimeUnit.SECONDS);
	}

	public static Single<TodoModel> addTodo(TodoModel todoModel) {
		return ApiClient.getClient()
				.create(ApiInterface.class)
				.addTodo(getRequestBody(todoModel))
				.timeout(TIMEOUT, TimeUnit.SECONDS);
	}

	public static Single<ResponseBody> deleteTodo(String todoId) {
		return ApiClient.getClient()
				.create(ApiInterface.class)
				.deleteTodo(todoId)
				.timeout(TIMEOUT, TimeUnit.SECONDS);
	}

	private static RequestBody getRequestBody(Object obj) {
		String jsonStr = new Gson().toJson(obj);
		MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
		RequestBody requestBody = RequestBody.create(mediaType, jsonStr);
		return requestBody;
	}
}
