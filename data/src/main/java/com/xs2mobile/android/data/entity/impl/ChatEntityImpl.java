package com.xs2mobile.buurapp.data.entity.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.xs2mobile.buurapp.data.entity.inter.ChatEntity;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ChatEntityImpl implements ChatEntity {

    private Long id;
    private String created;
    private List<Integer> subscribers;
    private String type;
    private Long creator;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getCreated() {
        return created;
    }

    @Override
    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public List<Integer> getSubscribers() {
        return subscribers;
    }

    @Override
    public void setSubscribers(List<Integer> subscribers) {
        this.subscribers = subscribers;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Long getCreator() {
        return creator;
    }

    @Override
    public void setCreator(Long creator) {
        this.creator = creator;
    }
}
