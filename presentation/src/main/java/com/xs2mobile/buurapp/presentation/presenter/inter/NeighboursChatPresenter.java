package com.xs2mobile.buurapp.presentation.presenter.inter;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.xs2mobile.buurapp.presentation.model.ChatModel;
import com.xs2mobile.buurapp.presentation.model.MessageModel;
import com.xs2mobile.buurapp.presentation.model.UserModel;
import com.xs2mobile.buurapp.presentation.view.inter.NeighboursChatView;

public interface NeighboursChatPresenter extends MvpPresenter<NeighboursChatView> {

    void onViewCreated();

    UserModel getUser();

    ChatModel getChat();

    void onMessageAdded(String text);

    void onMessageCreated(MessageModel message);

}
