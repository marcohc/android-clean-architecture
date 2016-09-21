package com.marcohc.architecture.app.presentation.story.user.list;

import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.presentation.view.BaseView;

import java.util.List;

interface UsersListView extends BaseView {

    void loadData(List<UserModel> modelList);
}
