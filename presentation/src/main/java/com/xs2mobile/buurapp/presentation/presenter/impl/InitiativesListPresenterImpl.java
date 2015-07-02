package com.xs2mobile.buurapp.presentation.presenter.impl;

import com.xs2mobile.buurapp.domain.interactor.impl.InitiativesListInteractorImpl;
import com.xs2mobile.buurapp.domain.interactor.inter.InitiativesListInteractor;
import com.xs2mobile.buurapp.presentation.model.InitiativeModel;
import com.xs2mobile.buurapp.presentation.presenter.BasePresenter;
import com.xs2mobile.buurapp.presentation.presenter.inter.InitiativesListPresenter;
import com.xs2mobile.buurapp.presentation.view.inter.InitiativesListView;

import java.util.List;

public class InitiativesListPresenterImpl extends BasePresenter<InitiativesListView, InitiativesListInteractor> implements InitiativesListPresenter {

    @Override
    public InitiativesListInteractor createInteractor() {
        return new InitiativesListInteractorImpl(this);
    }

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onItemClick(int position) {
        getView().goToInitiativeDetail(position);
    }

    @Override
    public void onViewCreated() {
        getView().showLoading(true);
        getInteractor().getInitiatives();
    }

    @Override
    public void onInitiativeActionClick() {
        getView().goToInitiativeCreation();
    }

    // ************************************************************************************************************************************************************************
    // * Interactor handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void loadData(List<InitiativeModel> itemsList) {
        getView().loadListData(itemsList);
    }
}
