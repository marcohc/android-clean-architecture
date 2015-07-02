package com.xs2mobile.buurapp.data.entity.inter;

import com.xs2mobile.buurapp.data.entity.Entity;

import java.util.List;

public interface ChatEntity extends Entity {

    Long getId();

    void setId(Long id);

    String getCreated();

    void setCreated(String created);

    List<Integer> getSubscribers();

    void setSubscribers(List<Integer> subscribers);

    String getType();

    void setType(String type);

    Long getCreator();

    void setCreator(Long creator);
}
