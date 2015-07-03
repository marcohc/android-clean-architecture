package com.marcohc.android.clean.architecture.presentation.presenter.inter;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.marcohc.android.clean.architecture.presentation.view.inter.MainView;

public interface MainPresenter extends MvpPresenter<MainView> {

    void onViewCreated();

}
