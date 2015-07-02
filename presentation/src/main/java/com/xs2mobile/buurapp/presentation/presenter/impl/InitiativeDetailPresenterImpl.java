package com.xs2mobile.buurapp.presentation.presenter.impl;

import com.squareup.otto.Subscribe;
import com.xs2mobile.buurapp.domain.bus.BusProvider;
import com.xs2mobile.buurapp.domain.bus.event.MessageReceivedEvent;
import com.xs2mobile.buurapp.domain.bus.response.GetMessagesResponse;
import com.xs2mobile.buurapp.domain.bus.response.SendMessageResponse;
import com.xs2mobile.buurapp.domain.interactor.impl.GetMessagesUseCase;
import com.xs2mobile.buurapp.domain.interactor.impl.InitiativeDetailInteractorImpl;
import com.xs2mobile.buurapp.domain.interactor.impl.SendMessageUseCase;
import com.xs2mobile.buurapp.domain.interactor.inter.InitiativeDetailInteractor;
import com.xs2mobile.buurapp.domain.repository.ChatRepository;
import com.xs2mobile.buurapp.domain.repository.UserRepository;
import com.xs2mobile.buurapp.presentation.model.ChatModel;
import com.xs2mobile.buurapp.presentation.model.InitiativeModel;
import com.xs2mobile.buurapp.presentation.model.MessageModel;
import com.xs2mobile.buurapp.presentation.model.UserModel;
import com.xs2mobile.buurapp.presentation.presenter.BasePresenter;
import com.xs2mobile.buurapp.presentation.presenter.inter.InitiativeDetailPresenter;
import com.xs2mobile.buurapp.presentation.view.inter.InitiativeDetailView;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class InitiativeDetailPresenterImpl extends BasePresenter<InitiativeDetailView, InitiativeDetailInteractor> implements InitiativeDetailPresenter {

    private InitiativeModel initiative;

    @Override
    public InitiativeDetailInteractor createInteractor() {
        return new InitiativeDetailInteractorImpl(this);
    }

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onViewCreated() {
        parseInitiative(getView().getInitiativeJson());
        onLoadDataRequest();
    }

    private void parseInitiative(String initiativeJson) {
        initiative = getInteractor().parseInitiative(initiativeJson);
    }

    @Override
    public void onEnableEditActionButtonClick() {
        getView().enableDetailsEditContainer(true);
    }

    @Override
    public void onEditActionButtonClick() {
        if (isValidaForm()) {
            getView().showLoading(true);
            InitiativeModel initiative = new InitiativeModel();
            initiative.setId(this.initiative.getId());
            initiative.setTitle(getView().getInitiativeTitle());
            initiative.setDescription(getView().getDescription());
            getInteractor().updateInitiative(initiative);
        }
    }

    @Override
    public void onUpdateInitiativeResponse(InitiativeModel initiative) {
        this.initiative = initiative;
        onLoadDataRequest();
        getView().enableDetailsEditContainer(false);
    }

    @Override
    public void onLoadDataRequest() {
        getView().showLoading(true);
        getView().loadInitiativeData(initiative);
        new GetMessagesUseCase(UserRepository.getInstance().getCurrentUser(), initiative.getChat().getId()).execute();
    }

    @Override
    public void onDeleteActionButtonClick() {
        getView().showLoading(true);
        getInteractor().deleteInitiative(initiative);
    }

    @Override
    public InitiativeModel getInitiative() {
        return initiative;
    }

    @Override
    public void onMessageAdded(String text) {
        new SendMessageUseCase(UserRepository.getInstance().getCurrentUser(), initiative.getChat().getId(), text).execute();
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
        if (message.getChatId() == initiative.getChat().getId().longValue()) {
            onMessageCreated(message);
        }
        // This message is not for this screen, create notification
        else {
            BusProvider.getInstance().post(BusProvider.produceCreateNotificationEvent(message));
        }
    }

    @Override
    public void goBack() {
        getView().showLoading(false);
        getView().goBack();
    }

    @Override
    public void loadData(List<MessageModel> itemsList) {
        getView().showLoading(false);
        getView().loadMessagesData(itemsList);
    }

    @Override
    public void onMessageCreated(MessageModel message) {
        getView().addMessage(message);
    }

    // ************************************************************************************************************************************************************************
    // * UI auxiliary methods
    // ************************************************************************************************************************************************************************

    private boolean isValidaForm() {

        if (StringUtils.isBlank(getView().getInitiativeTitle())) {
            getView().invalidateTitle();
            return false;
        }

        if (StringUtils.isBlank(getView().getDescription())) {
            getView().invalidateDescription();
            return false;
        }
        return true;
    }


}
