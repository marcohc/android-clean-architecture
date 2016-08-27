package com.marcohc.architecture.app.domain.bus.response.data;

import com.marcohc.architecture.app.domain.entity.UserEntity;
import com.marcohc.architecture.common.bus.BusEvent;

import java.util.List;

public class GetUsersDataResponse implements BusEvent {

    private final List<UserEntity> entityList;

    public GetUsersDataResponse(List<UserEntity> entityList) {
        this.entityList = entityList;
    }

    public List<UserEntity> getEntityList() {
        return entityList;
    }
}
