package com.marcohc.architecture.app.presentation.presenter.impl;

import com.marcohc.architecture.app.domain.bus.response.domain.GetUsersDomainResponse;
import com.marcohc.architecture.app.domain.interactor.GetCurrentUserUseCase;
import com.marcohc.architecture.app.domain.interactor.GetUsersUseCase;
import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.presentation.presenter.BasePresenter;
import com.marcohc.architecture.app.presentation.presenter.inter.UsersPresenter;
import com.marcohc.architecture.app.presentation.view.inter.UsersView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

@SuppressWarnings("ConstantConditions")
public class UsersPresenterImpl extends BasePresenter<UsersView> implements UsersPresenter {

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onViewCreated() {
        showLoadingDialog();
        new GetUsersUseCase().execute();
    }

    @Override
    public void onRefresh() {
        new GetUsersUseCase().execute();
    }

    @Override
    public UserModel getUser() {
        return new GetCurrentUserUseCase().execute();
    }

    // ************************************************************************************************************************************************************************
    // * Interactor handler methods
    // ************************************************************************************************************************************************************************

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(GetUsersDomainResponse event) {
        hideDialog();
        if (isViewAttached()) {
            getView().loadData(event.getUsersList());
        }
    }
}
