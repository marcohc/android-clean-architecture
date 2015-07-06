package com.marcohc.android.clean.architecture.domain.bus.event.request;

import com.marcohc.android.clean.architecture.domain.bus.event.BaseEvent;
import com.marcohc.android.clean.architecture.domain.entity.MessageEntity;

public class SendMessageRequest extends BaseEvent {

    public SendMessageRequest(MessageEntity transform) {

    }
}
