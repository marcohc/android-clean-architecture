package com.xs2mobile.buurapp.data.entity.inter;

import com.xs2mobile.buurapp.data.entity.Entity;
import com.xs2mobile.buurapp.data.entity.impl.AddressEntityImpl;
import com.xs2mobile.buurapp.data.entity.impl.ChatEntityImpl;

public interface InitiativeEntity extends Entity {

    void setId(Long id);

    ChatEntityImpl getChat();

    void setChat(ChatEntityImpl chat);

    String getDescription();

    void setDescription(String description);

    String getTitle();

    void setTitle(String title);

    AddressEntityImpl getLocation();

    void setLocation(AddressEntityImpl location);
}
