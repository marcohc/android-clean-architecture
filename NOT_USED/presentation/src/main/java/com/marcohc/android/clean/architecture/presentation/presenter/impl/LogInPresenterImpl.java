package com.marcohc.android.clean.architecture.presentation.presenter.impl;

import com.marcohc.android.clean.architecture.domain.bus.event.response.LogInEventResponse;
import com.marcohc.android.clean.architecture.domain.interactor.impl.LogInUseCase;
import com.marcohc.android.clean.architecture.presentation.presenter.BasePresenter;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.LogInPresenter;
import com.marcohc.android.clean.architecture.presentation.view.inter.LogInView;

import org.apache.commons.lang3.StringUtils;

public class LogInPresenterImpl extends BasePresenter<LogInView> implements LogInPresenter {

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onActionDoneClick() {
        if (isValidaForm()) {
            showLoading(true);
            new LogInUseCase(getView().getUsername(), getView().getPassword()).execute();
        }
    }

    // ************************************************************************************************************************************************************************
    // * Interactor handler methods
    // ************************************************************************************************************************************************************************

    public void onEventMainThread(LogInEventResponse event) {
        showLoading(false);
        getView().goToMain();
    }

    // ************************************************************************************************************************************************************************
    // * Presenter methods
    // ************************************************************************************************************************************************************************

    private boolean isValidaForm() {

        if (StringUtils.isBlank(getView().getUsername())) {
            getView().invalidateUsername();
            return false;
        }

        if (StringUtils.isBlank(getView().getPassword())) {
            getView().invalidatePassword();
            return false;
        }

        return true;
    }

}
