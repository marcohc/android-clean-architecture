package com.marcohc.architecture.app.presentation.presenter.inter;

import com.marcohc.architecture.app.presentation.activity.inter.MainView;
import com.marcohc.architecture.presentation.mosby.mvp.MvpPresenter;

public interface MainPresenter extends MvpPresenter<MainView> {

    void onViewCreated();

    void setSelectedMenuPosition(int position);
}
