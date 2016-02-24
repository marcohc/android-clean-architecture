package com.marcohc.architecture.presentation.presenter.inter;

import com.marcohc.architecture.presentation.mosby.mvp.MvpPresenter;
import com.marcohc.architecture.presentation.view.inter.ReportView;

public interface ReportPresenter extends MvpPresenter<ReportView> {

    void onActionDoneClick(String deviceValues);

}
