package com.marcohc.android.clean.architecture.presentation.presenter.impl;

import com.marcohc.android.clean.architecture.domain.bus.response.domain.GetUsersDomainResponse;
import com.marcohc.android.clean.architecture.domain.interactor.GetCurrentUserUseCase;
import com.marcohc.android.clean.architecture.domain.interactor.GetUsersUseCase;
import com.marcohc.android.clean.architecture.domain.model.UserModel;
import com.marcohc.android.clean.architecture.presentation.presenter.BasePresenter;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.UsersPresenter;
import com.marcohc.android.clean.architecture.presentation.view.inter.UsersView;

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
