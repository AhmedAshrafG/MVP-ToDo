package com.task.ahmedz.xtrava_todo.base;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by ahmed on 10-Jul-17.
 */

public class BaseFragment extends Fragment {
	private Context context;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		this.context = context;
	}
}
