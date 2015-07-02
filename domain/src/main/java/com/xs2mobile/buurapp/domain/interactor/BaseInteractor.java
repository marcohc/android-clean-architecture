package com.xs2mobile.buurapp.domain.interactor;

import android.os.AsyncTask;

import com.xs2mobile.buurapp.domain.repository.UserRepository;
import com.xs2mobile.buurapp.presentation.model.UserModel;
import com.xs2mobile.buurapp.presentation.presenter.BasePresenter;

public abstract class BaseInteractor<P extends BasePresenter> {

    protected P presenter;

    public BaseInteractor(P presenter) {
        this.presenter = presenter;
    }

    public UserModel getCurrentUser() {
        return UserRepository.getInstance().getCurrentUser();
    }

    public void execute(AsyncTask task) {
        if (task != null) {
            task.execute();
        }
    }
}
