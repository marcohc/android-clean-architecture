package com.xs2mobile.buurapp.presentation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.xs2mobile.buurapp.presentation.util.Constants;
import com.xs2mobile.buurapp.presentation.view.impl.adapter.viewholder.NeighbourViewHolder;
import com.xs2mobile.buurapp.presentation.view.impl.adapter.viewholder.ViewHolderAbstractClass;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NeighbourChatModel extends BaseModel {

    private UserModel user;
    private Long chatId;
    private int count;

    public NeighbourChatModel(UserModel user, Long chatId, int count) {
        this.user = user;
        this.chatId = chatId;
        this.count = count;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    @Override
    public ViewHolderAbstractClass getDefaultViewHolder(Constants.DATA_TYPE itemType) {
        return new NeighbourViewHolder();
    }
}
