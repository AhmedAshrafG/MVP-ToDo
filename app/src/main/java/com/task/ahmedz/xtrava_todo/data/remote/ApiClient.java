package com.task.ahmedz.xtrava_todo.data.remote;

import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ahmed on 10-Jul-17.
 */

public class ApiClient {

	public static final String BASE_URL = "https://todo-backend-modern-js.herokuapp.com/todos/";
	private static Retrofit retrofit = null;


	public static Retrofit getClient() {
		if (retrofit == null) {
			retrofit = new Retrofit.Builder()
					.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
					.addConverterFactory(GsonConverterFactory.create())
					.addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
							.serializeNulls()
							.excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
							.create()))
					.baseUrl(BASE_URL)
					.build();
		}
		return retrofit;
	}
}
