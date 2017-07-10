package com.task.ahmedz.xtrava_todo.data.remote;

import com.task.ahmedz.xtrava_todo.data.TodoModel;

import java.util.List;

import io.reactivex.Single;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ahmed on 10-Jul-17.
 */

public interface ApiInterface {
	@GET
	Single<List<TodoModel>> getTodoList();

	@POST
	Single<TodoModel> addTodo(@Body RequestBody requestBody);

	@PATCH("{todoId}")
	Single<TodoModel> updateTodo(@Path("todoId") String todoId, @Body RequestBody requestBody);

	@DELETE("{todoId}")
	Single<ResponseBody> deleteTodo(@Path("todoId") String todoId);
}
