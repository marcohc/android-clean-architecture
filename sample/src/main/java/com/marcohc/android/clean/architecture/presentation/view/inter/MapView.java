package com.marcohc.android.clean.architecture.presentation.view.inter;

import com.marcohc.android.clean.architecture.domain.model.UserModel;
import com.marcohc.android.clean.architecture.presentation.view.BaseView;

import java.util.List;

public interface MapView extends BaseView {

    void setMapItems(List<UserModel> items);

}
