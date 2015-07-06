package com.marcohc.android.clean.architecture.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public interface MessageEntity extends Entity {

    Long getSender();

    void setSender(Long sender);

    String getText();

    void setText(String text);

    Date getCreated();

    void setCreated(Date created);

    @JsonProperty("chat_id")
    Long getChatId();

    void setChatId(Long chatId);
}
