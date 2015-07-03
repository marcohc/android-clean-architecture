package com.marcohc.android.clean.architecture.common.bus.response;

import com.marcohc.android.clean.architecture.common.bus.event.BaseEvent;
import com.marcohc.android.clean.architecture.common.bus.event.Event;
import com.marcohc.android.clean.architecture.common.model.MessageModel;

public class SendMessageResponse extends BaseEvent implements Event {

    private MessageModel message;

    public SendMessageResponse(MessageModel message) {
        this.message = message;
    }

    public MessageModel getMessage() {
        return message;
    }
}
