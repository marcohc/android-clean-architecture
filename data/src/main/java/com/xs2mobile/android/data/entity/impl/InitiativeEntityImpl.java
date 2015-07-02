package com.xs2mobile.buurapp.data.entity.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.xs2mobile.buurapp.data.entity.inter.InitiativeEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class InitiativeEntityImpl implements InitiativeEntity {

    private Long id;
    private ChatEntityImpl chat;
    private String description;
    private String title;
    private AddressEntityImpl location;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public ChatEntityImpl getChat() {
        return chat;
    }

    @Override
    public void setChat(ChatEntityImpl chat) {
        this.chat = chat;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public AddressEntityImpl getLocation() {
        return location;
    }

    @Override
    public void setLocation(AddressEntityImpl location) {
        this.location = location;
    }

}
