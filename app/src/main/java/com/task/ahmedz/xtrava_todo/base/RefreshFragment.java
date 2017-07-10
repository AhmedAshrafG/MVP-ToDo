package com.task.ahmedz.xtrava_todo.base;

/**
 * Created by ahmed on 10-Jul-17.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.task.ahmedz.xtrava_todo.R;

import butterknife.ButterKnife;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public abstract class RefreshFragment extends BaseFragment {
	private SwipeRefreshLayout refreshLayout;
	private View contentView;
	protected Context context;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getActivity();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.refresh_fragment, container, false);
		FrameLayout contentContainer = (FrameLayout) root.findViewById(R.id.content_container);
		contentView = inflater.inflate(getLayoutResId(), null);
		contentContainer.addView(contentView, new FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
		initViews(root);
		setLoading();
		ButterKnife.bind(this, root);
		onLayoutInflated();
		return root;
	}

	protected void onLayoutInflated() {}

	protected abstract int getLayoutResId();

	private void initViews(View root) {
		refreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.swipe_container);
		refreshLayout.setOnRefreshListener(() -> onRefresh());
	}
	protected void setLoading() {
		contentView.setVisibility(View.GONE);
		refreshLayout.setRefreshing(true);
	}
	protected void setLoaded() {
		contentView.setVisibility(View.VISIBLE);
		refreshLayout.setRefreshing(false);
	}

	protected abstract void onRefresh();
}