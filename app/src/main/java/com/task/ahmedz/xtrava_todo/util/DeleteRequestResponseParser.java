package com.task.ahmedz.xtrava_todo.util;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Created by ahmed on 10-Jul-17.
 */

public class DeleteRequestResponseParser {
	private ResponseBody responseBody;

	public DeleteRequestResponseParser(ResponseBody responseBody) {
		this.responseBody = responseBody;
	}

	public boolean isSuccess() throws IOException, JSONException {
		String responseStr = responseBody.string();
		JSONArray jsonArray = new JSONArray(responseStr);
		return jsonArray.getInt(0) == 1;
	}
}
