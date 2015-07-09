package com.marcohc.android.clean.architecture.presentation.view.inter;

import com.marcohc.android.clean.architecture.domain.model.UserModel;

import java.util.List;

public interface LogInView extends BaseView {

    String getUsername();

    String getPassword();

    void invalidateUsername();

    void invalidatePassword();

    void loadData(List<UserModel> modelList);

    void goToMain();

    UserModel getUser();

}
