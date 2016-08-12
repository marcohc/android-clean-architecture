package com.marcohc.architecture.app.presentation.fragment.inter;

import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.presentation.view.BaseView;

import java.util.List;

public interface UsersListView extends BaseView {

    void loadData(List<UserModel> modelList);
}
