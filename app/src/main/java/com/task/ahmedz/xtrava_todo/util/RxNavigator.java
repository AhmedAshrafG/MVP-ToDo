package com.task.ahmedz.xtrava_todo.util;

import android.app.Application;
import android.content.Intent;
import android.support.v4.app.Fragment;

import io.reactivex.Observable;
import rx_activity_result2.Result;
import rx_activity_result2.RxActivityResult;

import static android.app.Activity.RESULT_OK;

/**
 * Created by ahmed on 10-Jul-17.
 */

public class RxNavigator {

	public static void register(Application application) {
		RxActivityResult.register(application);
	}

	public static <T extends Fragment> Observable<Result<T>> navigateAndWait(T fragment, Intent intent) {
		return RxActivityResult.on(fragment)
				.startIntent(intent)
				.filter(result -> result.resultCode() == RESULT_OK);
	}
}
