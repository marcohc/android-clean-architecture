package com.xs2mobile.buurapp.presentation.presenter.impl;

import com.xs2mobile.buurapp.domain.interactor.impl.InitiativeCreationInteractorImpl;
import com.xs2mobile.buurapp.domain.interactor.inter.InitiativeCreationInteractor;
import com.xs2mobile.buurapp.presentation.model.InitiativeModel;
import com.xs2mobile.buurapp.presentation.presenter.BasePresenter;
import com.xs2mobile.buurapp.presentation.presenter.inter.InitiativeCreationPresenter;
import com.xs2mobile.buurapp.presentation.view.inter.InitiativeCreationView;

import org.apache.commons.lang3.StringUtils;

public class InitiativeCreationPresenterImpl extends BasePresenter<InitiativeCreationView, InitiativeCreationInteractor> implements InitiativeCreationPresenter {

    @Override
    public InitiativeCreationInteractor createInteractor() {
        return new InitiativeCreationInteractorImpl(this);
    }

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onActionDoneClick() {
        if (isValidaForm()) {
            getView().showLoading(true);
            InitiativeModel initiative = new InitiativeModel();
            initiative.setTitle(getView().getInitiativeTitle());
            initiative.setDescription(getView().getDescription());
            getInteractor().createInitiative(initiative);
        }
    }

    @Override
    public void goToMain() {
        getView().goBack();
    }

    // ************************************************************************************************************************************************************************
    // * Interactor handler methods
    // ************************************************************************************************************************************************************************


    // ************************************************************************************************************************************************************************
    // * Presenter auxiliary methods
    // ************************************************************************************************************************************************************************

    private boolean isValidaForm() {

        if (StringUtils.isBlank(getView().getInitiativeTitle())) {
            getView().invalidateTitle();
            return false;
        }

        if (StringUtils.isBlank(getView().getDescription())) {
            getView().invalidateDescription();
            return false;
        }
        return true;
    }


}
