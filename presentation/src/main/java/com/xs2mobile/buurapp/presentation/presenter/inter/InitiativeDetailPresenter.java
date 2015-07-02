package com.xs2mobile.buurapp.presentation.presenter.inter;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.xs2mobile.buurapp.presentation.model.InitiativeModel;
import com.xs2mobile.buurapp.presentation.model.MessageModel;
import com.xs2mobile.buurapp.presentation.view.inter.InitiativeDetailView;

import java.util.List;

public interface InitiativeDetailPresenter extends MvpPresenter<InitiativeDetailView> {

    void onDeleteActionButtonClick();

    void goBack();

    void loadData(List<MessageModel> itemsList);

    void onMessageAdded(String text);

    void onLoadDataRequest();

    InitiativeModel getInitiative();

    void onViewCreated();

    void onMessageCreated(MessageModel message);

    void onEnableEditActionButtonClick();

    void onEditActionButtonClick();

    void onUpdateInitiativeResponse(InitiativeModel initiative);

}
