package com.task.ahmedz.xtrava_todo.edit_todo;

import android.text.TextUtils;

import com.task.ahmedz.xtrava_todo.edit_todo.repository.EditTodoRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ahmed on 16-Jul-17.
 */

public class EditTodoPresenter implements EditTodoContract.Presenter {

	private EditTodoContract.View mView;
	private String todoId;
	private final EditTodoRepository mRepository;
	private CompositeDisposable mSubscriptions;

	public EditTodoPresenter(EditTodoContract.View mView, EditTodoRepository mRepository, String todoId) {
		this.mView = mView;
		this.todoId = todoId;
		this.mRepository = mRepository;
		this.mSubscriptions = new CompositeDisposable();

		mView.setPresenter(this);
	}

	@Override
	public void subscribe() {
		loadTodo(todoId);
	}

	@Override
	public void unsubscribe() {
		mSubscriptions.dispose();
	}

	@Override
	public void onSubmitClicked(String title, String order) {
		if (TextUtils.isEmpty(title)) {
			mView.showTitleError();
			return;
		}
		if (TextUtils.isEmpty(order)) {
			mView.showOrderError();
			return;
		}

		mView.finishWithResult(title, Integer.valueOf(order));
	}

	@Override
	public void loadTodo(String todoId) {
		mSubscriptions.add(
				mRepository.loadTodoItem(todoId)
						.subscribeOn(Schedulers.newThread())
						.observeOn(AndroidSchedulers.mainThread())
						.subscribe(
								todoModel -> mView.showTodoDetails(todoModel.getTitle(), String.valueOf(todoModel.getOrder()))
								, Throwable::printStackTrace
						)
		);
	}
}
