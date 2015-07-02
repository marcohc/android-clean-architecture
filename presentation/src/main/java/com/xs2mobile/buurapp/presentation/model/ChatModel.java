package com.xs2mobile.buurapp.presentation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.xs2mobile.buurapp.presentation.view.impl.adapter.viewholder.ViewHolderAbstractClass;
import com.xs2mobile.buurapp.presentation.util.Constants;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatModel extends BaseModel {

    private Long id;
    private String created;
    private List<Long> subscribers;
    private String type;
    private Long creator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public List<Long> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Long> subscribers) {
        this.subscribers = subscribers;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    @Override
    public ViewHolderAbstractClass getDefaultViewHolder(Constants.DATA_TYPE itemType) {
        return null;
    }
}
