package com.xs2mobile.buurapp.presentation.presenter.impl;

import com.squareup.otto.Subscribe;
import com.xs2mobile.buurapp.data.datasource.DataError;
import com.xs2mobile.buurapp.domain.bus.BusProvider;
import com.xs2mobile.buurapp.domain.bus.event.MessageReceivedEvent;
import com.xs2mobile.buurapp.domain.bus.response.GetMessagesResponse;
import com.xs2mobile.buurapp.domain.bus.response.SendMessageResponse;
import com.xs2mobile.buurapp.domain.interactor.impl.GetMessagesUseCase;
import com.xs2mobile.buurapp.domain.interactor.impl.NeighboursChatInteractorImpl;
import com.xs2mobile.buurapp.domain.interactor.impl.SendMessageUseCase;
import com.xs2mobile.buurapp.domain.interactor.inter.NeighboursChatInteractor;
import com.xs2mobile.buurapp.domain.repository.ChatRepository;
import com.xs2mobile.buurapp.domain.repository.UserRepository;
import com.xs2mobile.buurapp.presentation.model.ChatModel;
import com.xs2mobile.buurapp.presentation.model.MessageModel;
import com.xs2mobile.buurapp.presentation.model.UserModel;
import com.xs2mobile.buurapp.presentation.presenter.BasePresenter;
import com.xs2mobile.buurapp.presentation.presenter.inter.NeighboursChatPresenter;
import com.xs2mobile.buurapp.presentation.view.inter.NeighboursChatView;

public class NeighboursChatPresenterImpl extends BasePresenter<NeighboursChatView, NeighboursChatInteractor> implements NeighboursChatPresenter {

    private ChatModel chat;
    private UserModel user;

    @Override
    public NeighboursChatInteractor createInteractor() {
        return new NeighboursChatInteractorImpl(this);
    }

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onViewCreated() {
        showLoading(true);
        parseUser(getView().getUserJson());
        parseChat(getView().getChatJson());
        getView().loadUserData(user);
        new GetMessagesUseCase(UserRepository.getInstance().getCurrentUser(), chat.getId()).execute();
        BusProvider.getInstance().post(BusProvider.produceResetChatCount(chat.getId()));
    }

    @Override
    public UserModel getUser() {
        return user;
    }

    @Override
    public ChatModel getChat() {
        return chat;
    }

    @Override
    public void onMessageAdded(String text) {
        new SendMessageUseCase(UserRepository.getInstance().getCurrentUser(), chat.getId(), text).execute();
    }

    // ************************************************************************************************************************************************************************
    // * Interactor handler methods
    // ************************************************************************************************************************************************************************

    @Subscribe
    public void onSendMessageResponse(SendMessageResponse event) {
        onMessageCreated(event.getMessage());
    }

    @Subscribe
    public void onGetMessagesResponse(GetMessagesResponse event) {
        showLoading(false);
        getView().loadMessagesData(event.getMessagesList());
    }

    @Subscribe
    public void onMessageReceived(MessageReceivedEvent messageReceivedEvent) {

        // Complete all fields of the message. Chat and users should be already in the memory
        MessageModel message = messageReceivedEvent.getMessage();
        ChatModel chat = ChatRepository.getInstance().getChat(message.getChatId());
        UserModel user = UserRepository.getInstance().getUser(message.getUser().getId());
        message.setUser(user);
        if (message.getChatId() == chat.getId().longValue()) {
            onMessageCreated(message);
        }
        // This message is not for this screen, create notification
        else {
            BusProvider.getInstance().post(BusProvider.produceCreateNotificationEvent(message));
        }
    }

    @Override
    public void onMessageCreated(MessageModel message) {
        getView().addMessage(message);
    }

    @Subscribe
    public void onErrorEvent(DataError error) {
        handleError(error);
    }

    // ************************************************************************************************************************************************************************
    // * UI auxiliary methods
    // ************************************************************************************************************************************************************************

    private void parseUser(String userJson) {
        user = getInteractor().parseUser(userJson);
    }

    private void parseChat(String chatJson) {
        chat = getInteractor().parseChat(chatJson);
    }

}
