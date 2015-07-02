package com.xs2mobile.buurapp.presentation.view.inter;

import com.xs2mobile.buurapp.presentation.model.MessageModel;
import com.xs2mobile.buurapp.presentation.model.UserModel;

import java.util.List;

public interface NeighboursChatView extends BaseView {

    String getChatJson();

    String getUserJson();

    void loadUserData(UserModel user);

    void loadMessagesData(List<MessageModel> messageModelList);

    void addMessage(MessageModel message);
}
