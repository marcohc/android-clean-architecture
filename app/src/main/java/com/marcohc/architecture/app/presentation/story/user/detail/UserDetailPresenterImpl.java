package com.marcohc.architecture.app.presentation.story.user.detail;

import com.marcohc.architecture.app.domain.mapper.UserMapper;
import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.presentation.presenter.BaseMvpPresenter;

@SuppressWarnings("ConstantConditions")
class UserDetailPresenterImpl extends BaseMvpPresenter<UserDetailView> implements UserDetailPresenter {

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onViewCreated(String userJson) {
        UserModel user = UserMapper.getInstance().parseModel(userJson);
        getView().setUserData(user);
    }

}
