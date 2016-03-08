package com.marcohc.architecture.app.presentation.presenter.inter;

import com.marcohc.architecture.presentation.mosby.mvp.MvpPresenter;
import com.marcohc.architecture.app.presentation.view.inter.MainView;

public interface MainPresenter extends MvpPresenter<MainView> {

    void onViewCreated();

    void setSelectedMenuPosition(int position);
}
