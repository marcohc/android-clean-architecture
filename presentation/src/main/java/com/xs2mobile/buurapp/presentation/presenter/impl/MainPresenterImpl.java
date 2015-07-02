package com.xs2mobile.buurapp.presentation.presenter.impl;

import com.squareup.otto.Subscribe;
import com.xs2mobile.buurapp.domain.interactor.impl.MainInteractorImpl;
import com.xs2mobile.buurapp.domain.interactor.inter.MainInteractor;
import com.xs2mobile.buurapp.domain.mapper.MessageMapper;
import com.xs2mobile.buurapp.domain.repository.ChatRepository;
import com.xs2mobile.buurapp.domain.repository.UserRepository;
import com.xs2mobile.buurapp.domain.bus.event.MessageReceivedEvent;
import com.xs2mobile.buurapp.presentation.model.ChatModel;
import com.xs2mobile.buurapp.presentation.model.InitiativeModel;
import com.xs2mobile.buurapp.presentation.model.MessageModel;
import com.xs2mobile.buurapp.presentation.model.UserModel;
import com.xs2mobile.buurapp.presentation.notification.NotificationManager;
import com.xs2mobile.buurapp.presentation.presenter.BasePresenter;
import com.xs2mobile.buurapp.presentation.presenter.inter.MainPresenter;
import com.xs2mobile.buurapp.presentation.view.inter.MainView;

public class MainPresenterImpl extends BasePresenter<MainView, MainInteractor> implements MainPresenter {

    @Override
    public MainInteractor createInteractor() {
        return new MainInteractorImpl(this);
    }

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onViewCreated() {
        getInteractor().initializeData();
    }

    @Override
    public void onNotificationReceived(String pushId, NotificationManager.NOTIFICATION_KEY notificationKey, String messageJson) {

        switch (notificationKey) {
            case MESSAGE_RECEIVED:

                // Complete all fields of the message. Chat and users should be already in the memory
                MessageModel message = MessageMapper.parse(messageJson);
                ChatModel chat = ChatRepository.getInstance().getChat(message.getChatId());
                UserModel user = UserRepository.getInstance().getUser(message.getUser().getId());

                if (message.getInitiativeId() != null) {
                    getInteractor().getInitiative(message.getInitiativeId());
                } else {
                    getView().goToNeighbourChat(user, chat);
                }
                break;
        }
    }

    @Subscribe
    public void onMessageReceived(MessageReceivedEvent messageReceivedEvent) {
        MessageModel message = messageReceivedEvent.getMessage();
        getView().onMessageReceived(message);
    }

    // ************************************************************************************************************************************************************************
    // * Presenter methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onViewCreatedResponse() {
        getView().onViewCreatedResponse();
    }

    @Override
    public void onGetInitiativeResponse(InitiativeModel model) {
        getView().goToInitiativeDetail(model);
    }
}
