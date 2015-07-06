package com.marcohc.android.clean.architecture.domain.bus.event.request;


import com.marcohc.android.clean.architecture.domain.bus.event.BaseEvent;

public class SendMessageEvent extends BaseEvent {

    private Long chatId;
    private String text;

    public SendMessageEvent(Long chatId, String text) {
        this.chatId = chatId;
        this.text = text;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
