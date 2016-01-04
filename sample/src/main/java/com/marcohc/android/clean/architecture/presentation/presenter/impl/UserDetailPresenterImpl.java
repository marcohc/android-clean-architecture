package com.marcohc.android.clean.architecture.presentation.presenter.impl;

import com.marcohc.android.clean.architecture.domain.mapper.UserMapper;
import com.marcohc.android.clean.architecture.domain.model.UserModel;
import com.marcohc.android.clean.architecture.presentation.BasePresenter;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.UserDetailPresenter;
import com.marcohc.android.clean.architecture.presentation.view.inter.UserDetailView;

@SuppressWarnings("ConstantConditions")
public class UserDetailPresenterImpl extends BasePresenter<UserDetailView> implements UserDetailPresenter {

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onViewCreated(String userJson) {
        UserModel user = UserMapper.parseUser(userJson);
        if (isViewAttached()) {
            getView().setUserData(user);
        }
    }

    // ************************************************************************************************************************************************************************
    // * Interactor handler methods
    // ************************************************************************************************************************************************************************

//    public void onEventMainThread(GetUsersDomainResponse event) {
//        hideDialog();
//        if (isViewAttached()) {
//            getView().loadData(event.getUsersList());
//        }
//    }
}
