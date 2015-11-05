package com.marcohc.android.clean.architecture.presentation.presenter.impl;

import com.marcohc.android.clean.architecture.domain.bus.event.response.GetUsersResponse;
import com.marcohc.android.clean.architecture.domain.interactor.impl.GetUserUseCase;
import com.marcohc.android.clean.architecture.domain.interactor.impl.GetUsersUseCase;
import com.marcohc.android.clean.architecture.domain.model.UserModel;
import com.marcohc.android.clean.architecture.presentation.presenter.BasePresenter;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.ProfilePresenter;
import com.marcohc.android.clean.architecture.presentation.view.inter.ProfileView;

public class ProfilePresenterImpl extends BasePresenter<ProfileView> implements ProfilePresenter {

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onViewCreated() {
        showLoading(true);
        new GetUsersUseCase().execute();
    }

    @Override
    public UserModel getUser() {
        return new GetUserUseCase().execute();
    }

    // ************************************************************************************************************************************************************************
    // * Interactor handler methods
    // ************************************************************************************************************************************************************************

    public void onEventMainThread(GetUsersResponse event) {
        showLoading(false);
        getView().loadData(event.getUsersList());
    }
}
