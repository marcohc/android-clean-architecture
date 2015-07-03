package com.marcohc.android.clean.architecture.domain.interactor.impl;

import com.marcohc.android.clean.architecture.common.bus.event.SendMessageEvent;
import com.marcohc.android.clean.architecture.common.bus.response.SendMessageResponse;
import com.marcohc.android.clean.architecture.common.exception.DataException;
import com.marcohc.android.clean.architecture.common.model.MessageModel;
import com.marcohc.android.clean.architecture.domain.interactor.inter.BaseUseCase;
import com.marcohc.android.clean.architecture.domain.mapper.MessageMapper;
import com.squareup.otto.Subscribe;

import org.json.JSONObject;

import java.util.Date;

// TODO: Try communication with repository by bus
// TODO: Create boundaries for presentation/domain and domain/data
public class SendMessageUseCase extends BaseUseCase {

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
    public SendMessageEvent createEvent() {
        return new SendMessageEvent(chatId, text);
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
    public void onEventReceived(SendMessageEvent event) {

        message = new MessageModel();
        message.setChatId(event.getChatId());
        message.setText(event.getText());
        message.setCreated(new Date(System.currentTimeMillis()));
        message.setMine(true);

        post(new SendMessageRequest(MessageMapper.transform(message)));

//        MessageRepository.getInstance().create(MessageMapper.transform(message), "TOKEN_HERE", new RepositoryCallback<JSONObject>() {
//            @Override
//            public void failure(DataException error) {
//                postException(error);
//                unregisterFromBus();
//            }
//
//            @Override
//            public void success(JSONObject response) {
//                DataException error = DataException.getError(response);
//                if (error == null) {
//                    post(createResponse());
//                } else {
//                    failure(error);
//                }
//                unregisterFromBus();
//            }
//        });
    }

    @Subscribe
    public void onResponseReceived(JSONObject response) {
        DataException error = DataException.getError(response);
        if (error == null) {
            // TODO: PARSE OR MAP RESPONSE
            post(createResponse());
            unregisterFromBus();
        } else {
            handleException(error);
        }
    }

    @Subscribe
    public void onExceptionReceived(DataException error) {
        handleException(error);
    }

}
