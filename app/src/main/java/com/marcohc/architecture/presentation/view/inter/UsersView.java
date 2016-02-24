package com.marcohc.architecture.presentation.view.inter;

import com.marcohc.architecture.domain.model.UserModel;
import com.marcohc.architecture.presentation.view.BaseView;

import java.util.List;

public interface UsersView extends BaseView {

    void loadData(List<UserModel> modelList);
}
