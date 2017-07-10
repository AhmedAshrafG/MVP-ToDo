package com.task.ahmedz.xtrava_todo.data.remote;

import com.google.gson.Gson;
import com.task.ahmedz.xtrava_todo.data.TodoModel;

import java.util.List;

import io.reactivex.Single;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by ahmed on 10-Jul-17.
 */

public class ApiRequests {

	public static Single<List<TodoModel>> getTodoList() {
		return ApiClient.getClient()
				.create(ApiInterface.class)
				.getTodoList();
	}

	private static RequestBody getRequestBody(Object obj) {
		String jsonStr = new Gson().toJson(obj);
		MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
		RequestBody requestBody = RequestBody.create(mediaType, jsonStr);
		return requestBody;
	}
}
