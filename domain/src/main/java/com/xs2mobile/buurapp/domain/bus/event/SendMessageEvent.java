package com.xs2mobile.buurapp.domain.bus.event;

import com.xs2mobile.buurapp.presentation.model.UserModel;

public class SendMessageEvent implements Event {

    private UserModel user;
    private Long chatId;
    private String text;

    public SendMessageEvent(UserModel user, Long chatId, String text) {
        this.user = user;
        this.chatId = chatId;
        this.text = text;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
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
