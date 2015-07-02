package com.xs2mobile.buurapp.presentation.view.inter;

import com.hannesdorfmann.mosby.mvp.MvpView;

public interface BaseView extends MvpView {

    void showLoading(boolean show);

    void showError(String error);

    String getResourceString(int stringId);

}
