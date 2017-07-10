package com.task.ahmedz.xtrava_todo.base;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.widget.Toast;

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

	protected void showSnackBar(String message) {
		Snackbar snackbar = Snackbar.make(getView(), message, Toast.LENGTH_SHORT);
		snackbar.setAction("Dismiss", v -> snackbar.dismiss());
		snackbar.show();
	}

	protected void showSnackBar(int resId) {
		showSnackBar(getString(resId));
	}
}
