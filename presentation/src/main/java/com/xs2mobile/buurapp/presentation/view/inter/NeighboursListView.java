package com.xs2mobile.buurapp.presentation.view.inter;

import com.xs2mobile.buurapp.presentation.model.ChatModel;
import com.xs2mobile.buurapp.presentation.model.NeighbourChatModel;
import com.xs2mobile.buurapp.presentation.model.UserModel;

import java.util.List;

public interface NeighboursListView extends BaseView {

    void loadData(List<NeighbourChatModel> modelList);

    void goToNeighboursChat(UserModel user, ChatModel chat);
}
