package com.marcohc.architecture.presentation.presenter.impl;

import android.os.Handler;

import com.marcohc.architecture.presentation.presenter.BasePresenter;
import com.marcohc.architecture.presentation.presenter.inter.ReportPresenter;
import com.marcohc.architecture.presentation.view.inter.ReportView;
import com.marcohc.architecture.sample.R;
import com.marcohc.architecture.common.helper.StringHelper;

@SuppressWarnings("ConstantConditions")
public class ReportPresenterImpl extends BasePresenter<ReportView> implements ReportPresenter {

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onActionDoneClick(final String deviceValues) {
        if (isViewAttached()) {
            if (isFormValid()) {
                showDialog(getView().getResourceString(R.string.report), getView().getResourceString(R.string.loading), false);
                // TODO
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hideDialog();
                        showInfo(String.format("%s: %s", getView().getResourceString(R.string.report_sent), deviceValues));
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
