package com.xs2mobile.buurapp.presentation.presenter.inter;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.xs2mobile.buurapp.presentation.view.inter.InitiativeCreationView;

public interface InitiativeCreationPresenter extends MvpPresenter<InitiativeCreationView> {

    void onActionDoneClick();

    void goToMain();

}
