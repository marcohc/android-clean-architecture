package com.xs2mobile.buurapp.domain.bus.response;

import com.xs2mobile.buurapp.domain.bus.event.BaseEvent;
import com.xs2mobile.buurapp.domain.bus.event.Event;
import com.xs2mobile.buurapp.presentation.model.MessageModel;

public class SendMessageResponse extends BaseEvent implements Event {

    private MessageModel message;

    public SendMessageResponse(MessageModel message) {
        this.message = message;
    }

    public MessageModel getMessage() {
        return message;
    }
}
