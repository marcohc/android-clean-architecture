package com.xs2mobile.buurapp.presentation.view.inter;

import com.xs2mobile.buurapp.presentation.model.ChatModel;
import com.xs2mobile.buurapp.presentation.model.InitiativeModel;
import com.xs2mobile.buurapp.presentation.model.MessageModel;
import com.xs2mobile.buurapp.presentation.model.UserModel;

public interface MainView extends BaseView {

    void onMessageReceived(MessageModel message);

    void onViewCreatedResponse();

    void goToInitiativeDetail(InitiativeModel model);

    void goToNeighbourChat(UserModel user, ChatModel chat);
}
