package com.marcohc.android.clean.architecture.presentation.presenter.impl;

import android.os.Handler;

import com.marcohc.android.clean.architecture.domain.bus.response.domain.SignUpDomainResponse;
import com.marcohc.android.clean.architecture.domain.interactor.SignUpUseCase;
import com.marcohc.android.clean.architecture.presentation.BasePresenter;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.ReportPresenter;
import com.marcohc.android.clean.architecture.presentation.view.inter.ReportView;
import com.marcohc.android.clean.architecture.sample.R;
import com.marcohc.helperoid.StringHelper;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ConstantConditions")
public class ReportPresenterImpl extends BasePresenter<ReportView> implements ReportPresenter {

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onActionDoneClick() {
        if (isViewAttached()) {
            if (isFormValid()) {
                showDialog(getView().getResourceString(R.string.report), getView().getResourceString(R.string.loading), false);
                // TODO
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hideDialog();
                        showInfo(getView().getResourceString(R.string.report_sent));
                    }
                }, 1500);
            }
        }
    }

    // ************************************************************************************************************************************************************************
    // * Interactor handler methods
    // ************************************************************************************************************************************************************************

    // ************************************************************************************************************************************************************************
    // * Presenter methods
    // ************************************************************************************************************************************************************************

    private boolean isFormValid() {

        if (StringHelper.isEmpty(getView().getReportText())) {
            getView().invalidateReportSpinner();
            return false;
        }

        if (StringHelper.isEmpty(getView().getDescriptionText())) {
            getView().invalidateDescription();
            return false;
        }

        return true;
    }

}
