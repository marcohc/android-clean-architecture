package com.marcohc.android.clean.architecture.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.marcohc.android.clean.architecture.domain.entity.MessageEntity;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MessageEntityImpl implements MessageEntity {

    private Long id;
    private Long sender;
    @JsonProperty("chat_id")
    private Long chatId;
    private String text;
    private Date created;

    @Override
    public Long getSender() {
        return sender;
    }

    @Override
    public void setSender(Long sender) {
        this.sender = sender;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Date getCreated() {
        return created;
    }

    @Override
    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    @JsonProperty("chat_id")
    public Long getChatId() {
        return chatId;
    }

    @Override
    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }
}
