package com.marcohc.android.clean.architecture.domain.bus.event.response;

import com.marcohc.android.clean.architecture.domain.bus.event.BaseEvent;
import com.marcohc.android.clean.architecture.domain.model.MessageModel;

public class SendMessageEventResponse extends BaseEvent {

    private MessageModel message;

    public SendMessageEventResponse(MessageModel message) {
        this.message = message;
    }

    public MessageModel getMessage() {
        return message;
    }
}
