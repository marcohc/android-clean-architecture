package com.xs2mobile.buurapp.presentation.presenter.inter;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.xs2mobile.buurapp.presentation.model.InitiativeModel;
import com.xs2mobile.buurapp.presentation.view.inter.InitiativesListView;

import java.util.List;

public interface InitiativesListPresenter extends MvpPresenter<InitiativesListView> {

    void onItemClick(int position);

    void onViewCreated();

    void onInitiativeActionClick();

    void loadData(List<InitiativeModel> itemsList);
}
