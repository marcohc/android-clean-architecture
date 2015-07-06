package com.marcohc.android.clean.architecture.domain.interactor.impl;

import com.marcohc.android.clean.architecture.common.exception.DataException;
import com.marcohc.android.clean.architecture.domain.bus.event.request.SendMessageEvent;
import com.marcohc.android.clean.architecture.domain.bus.event.request.SendMessageRequest;
import com.marcohc.android.clean.architecture.domain.bus.event.response.SendMessageEventResponse;
import com.marcohc.android.clean.architecture.domain.interactor.inter.AsynchronousUseCase;
import com.marcohc.android.clean.architecture.domain.mapper.MessageMapper;
import com.marcohc.android.clean.architecture.domain.model.MessageModel;

import org.json.JSONObject;

import java.util.Date;

// TODO: Try communication with repository by bus
// TODO: Create boundaries for presentation/domain and domain/data
public class SendMessageUseCase extends AsynchronousUseCase {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private final Long chatId;
    private final String text;
    private MessageModel message;

    // ************************************************************************************************************************************************************************
    // * Constructor
    // ************************************************************************************************************************************************************************

    public SendMessageUseCase(Long chatId, String text) {
        this.chatId = chatId;
        this.text = text;
        registerToBus();
    }

    // ************************************************************************************************************************************************************************
    // * Event creation
    // ************************************************************************************************************************************************************************

    @Override
    protected SendMessageEvent createEvent() {
        return new SendMessageEvent(chatId, text);
    }

    // ************************************************************************************************************************************************************************
    // * Response creation
    // ************************************************************************************************************************************************************************

    @Override
    protected SendMessageEventResponse createResponse() {
        return new SendMessageEventResponse(message);
    }

    // ************************************************************************************************************************************************************************
    // * Use case execution
    // ************************************************************************************************************************************************************************

    protected void onEvent(SendMessageEvent event) {

        message = new MessageModel();
        message.setChatId(event.getChatId());
        message.setText(event.getText());
        message.setCreated(new Date(System.currentTimeMillis()));
        message.setMine(true);

        post(new SendMessageRequest(MessageMapper.transform(message)));
    }

    protected void onEvent(JSONObject response) {
        DataException error = DataException.getError(response);
        if (error == null) {
            // TODO: PARSE OR MAP RESPONSE
            post(createResponse());
            unregisterFromBus();
        } else {
            handleException(error);
        }
    }
}
