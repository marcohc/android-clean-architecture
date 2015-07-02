package com.xs2mobile.buurapp.presentation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.xs2mobile.buurapp.presentation.util.Constants;
import com.xs2mobile.buurapp.presentation.view.impl.adapter.viewholder.InitiativeViewHolder;
import com.xs2mobile.buurapp.presentation.view.impl.adapter.viewholder.ViewHolderAbstractClass;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class InitiativeModel extends BaseModel {

    private Long id;
    private ChatModel chat;
    private String description;
    private String title;
    private AddressModel location;
    private boolean mine;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChatModel getChat() {
        return chat;
    }

    public void setChat(ChatModel chat) {
        this.chat = chat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AddressModel getLocation() {
        return location;
    }

    public void setLocation(AddressModel location) {
        this.location = location;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    @Override
    public ViewHolderAbstractClass getDefaultViewHolder(Constants.DATA_TYPE itemType) {
        return new InitiativeViewHolder();
    }
}
