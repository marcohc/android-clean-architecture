package com.marcohc.architecture.app.presentation.presenter.impl;

import com.marcohc.architecture.app.domain.mapper.UserMapper;
import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.app.presentation.presenter.BasePresenter;
import com.marcohc.architecture.app.presentation.presenter.inter.UserDetailPresenter;
import com.marcohc.architecture.app.presentation.view.inter.UserDetailView;

@SuppressWarnings("ConstantConditions")
public class UserDetailPresenterImpl extends BasePresenter<UserDetailView> implements UserDetailPresenter {

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onViewCreated(String userJson) {
        UserModel user = UserMapper.getInstance().parseModel(userJson);
        if (isViewAttached()) {
            getView().setUserData(user);
        }
    }

    // ************************************************************************************************************************************************************************
    // * Interactor handler methods
    // ************************************************************************************************************************************************************************

//        @Subscribe(threadMode = ThreadMode.MAIN) public void onEventMainThread(GetUsersDomainResponse event) {
//        hideDialog();
//        if (isViewAttached()) {
//            getView().loadData(event.getUsersList());
//        }
//    }
}
