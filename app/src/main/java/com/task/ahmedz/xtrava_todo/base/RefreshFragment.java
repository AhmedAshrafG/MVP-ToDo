package com.task.ahmedz.xtrava_todo.base;

/**
 * Created by ahmed on 10-Jul-17.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.task.ahmedz.xtrava_todo.R;

import butterknife.ButterKnife;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public abstract class RefreshFragment extends BaseFragment {
	private SwipeRefreshLayout refreshLayout;
	private View contentView;
	private TextView emptyMessage;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = setContentView(inflater, container, getLayoutResId());
		onLayoutInflated();
		return view;
	}

	protected void onLayoutInflated() {
	}

	protected abstract int getLayoutResId();

	public View setContentView(LayoutInflater inflater, ViewGroup parent, int layoutResId) {
		View root = inflater.inflate(R.layout.base_fragment, parent, false);
		FrameLayout contentContainer = (FrameLayout) root.findViewById(R.id.content_container);
		contentView = inflater.inflate(layoutResId, null);
		contentContainer.addView(contentView, 0, new FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
		ButterKnife.bind(this, root);
		initViews(root);
		setLoading();
		return root;
	}
	private void initViews(View root) {
		emptyMessage = (TextView) root.findViewById(R.id.empty_message);
		refreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.swipe_container);
		refreshLayout.setOnRefreshListener(() -> onRefresh());
	}
	protected void setLoading() {
		contentView.setVisibility(View.INVISIBLE);
		emptyMessage.setVisibility(View.INVISIBLE);
		refreshLayout.setRefreshing(true);
	}
	protected void setLoaded(int stringResId) {
		contentView.setVisibility(View.VISIBLE);
		emptyMessage.setVisibility(View.VISIBLE);
		emptyMessage.setText(stringResId);
		refreshLayout.setRefreshing(false);
	}
	protected void setLoaded() {
		contentView.setVisibility(View.VISIBLE);
		emptyMessage.setVisibility(View.INVISIBLE);
		refreshLayout.setRefreshing(false);
	}

	protected abstract void onRefresh();
}
