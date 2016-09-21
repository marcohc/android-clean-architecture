package com.marcohc.architecture.app.presentation.story.main;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

public interface MainPresenter extends MvpPresenter<MainView> {

    void onViewCreated();

    void setSelectedMenuPosition(int position);
}
