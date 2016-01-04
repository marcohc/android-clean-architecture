package com.marcohc.android.clean.architecture.presentation.presenter.impl;

import com.marcohc.android.clean.architecture.domain.bus.response.domain.GetUsersDomainResponse;
import com.marcohc.android.clean.architecture.domain.interactor.GetUserUseCase;
import com.marcohc.android.clean.architecture.domain.interactor.GetUsersUseCase;
import com.marcohc.android.clean.architecture.domain.model.UserModel;
import com.marcohc.android.clean.architecture.presentation.BasePresenter;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.UsersPresenter;
import com.marcohc.android.clean.architecture.presentation.view.inter.UsersView;

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
        return new GetUserUseCase().execute();
    }

    // ************************************************************************************************************************************************************************
    // * Interactor handler methods
    // ************************************************************************************************************************************************************************

    public void onEventMainThread(GetUsersDomainResponse event) {
        hideDialog();
        if (isViewAttached()) {
            getView().loadData(event.getUsersList());
        }
    }
}
