package com.task.ahmedz.xtrava_todo.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.rits.cloning.Cloner;
import com.task.ahmedz.xtrava_todo.data.TodoModel;

/**
 * Created by ahmed on 10-Jul-17.
 */

public class Utils {
	private boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivityManager
				= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	public static TodoModel deepClone(TodoModel todoItem) {
		Cloner cloner = new Cloner();
		return cloner.deepClone(todoItem);
	}
}
