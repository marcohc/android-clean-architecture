package com.marcohc.architecture.presentation.presenter.inter;

import com.marcohc.architecture.presentation.mosby.mvp.MvpPresenter;
import com.marcohc.architecture.presentation.view.inter.MainView;

public interface MainPresenter extends MvpPresenter<MainView> {

    void onViewCreated();

    void setSelectedMenuPosition(int position);
}
