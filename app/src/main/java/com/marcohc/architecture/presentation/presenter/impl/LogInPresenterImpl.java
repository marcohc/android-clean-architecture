package com.marcohc.architecture.presentation.presenter.impl;

import com.marcohc.architecture.domain.bus.response.domain.LogInDomainResponse;
import com.marcohc.architecture.domain.interactor.LogInUseCase;
import com.marcohc.architecture.presentation.presenter.BasePresenter;
import com.marcohc.architecture.presentation.presenter.inter.LogInPresenter;
import com.marcohc.architecture.presentation.view.inter.LogInView;
import com.marcohc.architecture.common.helper.StringHelper;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class LogInPresenterImpl extends BasePresenter<LogInView> implements LogInPresenter {

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onActionDoneClick() {
        if (isFormValid()) {
            showLoadingDialog();
            new LogInUseCase(getView().getUsername(), getView().getPassword()).execute();
        }
    }

    // ************************************************************************************************************************************************************************
    // * Interactor handler methods
    // ************************************************************************************************************************************************************************

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(LogInDomainResponse event) {
        hideDialog();
        getView().goToMain();
    }

    // ************************************************************************************************************************************************************************
    // * Presenter methods
    // ************************************************************************************************************************************************************************

    private boolean isFormValid() {

        if (StringHelper.isEmpty(getView().getUsername())) {
            getView().invalidateUsername();
            return false;
        }

        if (StringHelper.isEmpty(getView().getPassword())) {
            getView().invalidatePassword();
            return false;
        }

        return true;
    }

}
