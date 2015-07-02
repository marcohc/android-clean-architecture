package com.xs2mobile.buurapp.domain.interactor.impl;

import com.squareup.otto.Subscribe;
import com.xs2mobile.buurapp.data.datasource.DataError;
import com.xs2mobile.buurapp.data.datasource.rest.RepositoryCallback;
import com.xs2mobile.buurapp.domain.bus.event.SendMessageEvent;
import com.xs2mobile.buurapp.domain.bus.response.SendMessageResponse;
import com.xs2mobile.buurapp.domain.interactor.inter.BaseUseCase;
import com.xs2mobile.buurapp.domain.mapper.MessageMapper;
import com.xs2mobile.buurapp.domain.repository.MessageRepository;
import com.xs2mobile.buurapp.presentation.model.MessageModel;
import com.xs2mobile.buurapp.presentation.model.UserModel;

import org.json.JSONObject;

import java.util.Date;

public class SendMessageUseCase extends BaseUseCase {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private final UserModel user;
    private final Long chatId;
    private final String text;
    private MessageModel message;

    // ************************************************************************************************************************************************************************
    // * Constructor
    // ************************************************************************************************************************************************************************

    public SendMessageUseCase(UserModel user, Long chatId, String text) {
        this.user = user;
        this.chatId = chatId;
        this.text = text;
        registerToBus();
    }

    // ************************************************************************************************************************************************************************
    // * Event creation
    // ************************************************************************************************************************************************************************

    @Override
    public SendMessageEvent createEvent() {
        return new SendMessageEvent(user, chatId, text);
    }

    // ************************************************************************************************************************************************************************
    // * Response creation
    // ************************************************************************************************************************************************************************

    @Override
    public SendMessageResponse createResponse() {
        return new SendMessageResponse(message);
    }

    // ************************************************************************************************************************************************************************
    // * Use case execution
    // ************************************************************************************************************************************************************************

    @Subscribe
    public void onExecuteEvent(SendMessageEvent event) {

        UserModel user = event.getUser();
        message = new MessageModel();
        message.setChatId(event.getChatId());
        message.setText(event.getText());
        message.setCreated(new Date(System.currentTimeMillis()));
        message.setUser(user);
        message.setMine(true);

        MessageRepository.getInstance().create(MessageMapper.transform(message), user.getToken(), new RepositoryCallback<JSONObject>() {
            @Override
            public void failure(DataError error) {
                postError(error);
                unregisterFromBus();
            }

            @Override
            public void success(JSONObject response) {
                DataError error = DataError.getError(response);
                if (error == null) {
                    post(createResponse());
                } else {
                    failure(error);
                }
                unregisterFromBus();
            }
        });
    }
}
