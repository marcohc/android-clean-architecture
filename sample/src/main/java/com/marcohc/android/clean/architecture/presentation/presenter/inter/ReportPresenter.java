package com.marcohc.android.clean.architecture.presentation.presenter.inter;

import com.marcohc.android.clean.architecture.presentation.mosby.mvp.MvpPresenter;
import com.marcohc.android.clean.architecture.presentation.view.inter.ReportView;

public interface ReportPresenter extends MvpPresenter<ReportView> {

    void onActionDoneClick(String deviceValues);

}
