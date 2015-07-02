package com.xs2mobile.buurapp.presentation.view.inter;

import com.xs2mobile.buurapp.presentation.model.InitiativeModel;
import com.xs2mobile.buurapp.presentation.model.MessageModel;

import java.util.List;

public interface InitiativeDetailView extends BaseView {

    void loadInitiativeData(InitiativeModel initiative);

    void loadMessagesData(List<MessageModel> messageModelList);

    String getInitiativeJson();

    void addMessage(MessageModel message);

    void enableDetailsEditContainer(boolean enable);

    String getInitiativeTitle();

    String getDescription();

    void invalidateDescription();

    void invalidateTitle();

    void goBack();
}
