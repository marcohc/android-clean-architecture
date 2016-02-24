package com.marcohc.architecture.presentation.presenter.impl;

import com.marcohc.architecture.domain.bus.response.domain.SignUpDomainResponse;
import com.marcohc.architecture.domain.interactor.SignUpUseCase;
import com.marcohc.architecture.presentation.presenter.BasePresenter;
import com.marcohc.architecture.presentation.presenter.inter.SignUpPresenter;
import com.marcohc.architecture.presentation.view.inter.SignUpView;
import com.marcohc.architecture.sample.R;
import com.marcohc.helperoid.StringHelper;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ConstantConditions")
public class SignUpPresenterImpl extends BasePresenter<SignUpView> implements SignUpPresenter {

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onActionDoneClick() {
        if (isViewAttached()) {
            if (isFormValid()) {
                showDialog(getView().getResourceString(R.string.sign_up), getView().getResourceString(R.string.loading), false);
                Map<String, Object> parametersMap = new HashMap<>();
                parametersMap.put("firstName", getView().getFirstName());
                parametersMap.put("lastName", getView().getLastName());
                parametersMap.put("email", getView().getEmail());
                parametersMap.put("password", getView().getPassword());
                new SignUpUseCase(parametersMap).execute();
            }
        }
    }

    // ************************************************************************************************************************************************************************
    // * Interactor handler methods
    // ************************************************************************************************************************************************************************

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(SignUpDomainResponse response) {
        hideDialog();
        if (isViewAttached()) {
            if (response.hasError()) {
                showInfo(getView().getResourceString(R.string.sign_up_error));
            } else {
                getView().goToMain();
            }
        }
    }

    // ************************************************************************************************************************************************************************
    // * Presenter methods
    // ************************************************************************************************************************************************************************

    private boolean isFormValid() {

        if (StringHelper.isEmpty(getView().getFirstName())) {
            getView().invalidateFirstName();
            return false;
        }

        if (StringHelper.isEmpty(getView().getLastName())) {
            getView().invalidateLastName();
            return false;
        }

        final String email = getView().getEmail();
        if (StringHelper.isEmpty(email) || !StringHelper.isEmailValid(email)) {
            getView().invalidateEmail();
            return false;
        }

        if (StringHelper.isEmpty(getView().getPassword())) {
            getView().invalidatePassword();
            return false;
        }

        if (StringHelper.isEmpty(getView().getConfirmPassword())) {
            getView().invalidateConfirmPassword();
            return false;
        }

        if (!getView().getPassword().equals(getView().getConfirmPassword())) {
            getView().invalidatePassword();
            getView().invalidateConfirmPassword();
            return false;
        }

        return true;
    }

}
