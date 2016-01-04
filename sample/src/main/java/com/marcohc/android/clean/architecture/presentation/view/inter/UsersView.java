package com.marcohc.android.clean.architecture.presentation.view.inter;

import com.marcohc.android.clean.architecture.domain.model.UserModel;
import com.marcohc.android.clean.architecture.presentation.view.BaseView;

import java.util.List;

public interface UsersView extends BaseView {

    void loadData(List<UserModel> modelList);
}
