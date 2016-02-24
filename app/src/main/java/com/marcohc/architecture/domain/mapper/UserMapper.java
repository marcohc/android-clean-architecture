package com.marcohc.architecture.domain.mapper;

import com.marcohc.architecture.domain.entity.UserEntity;
import com.marcohc.architecture.domain.model.UserModel;

public class UserMapper extends BaseMapper<UserModel, UserEntity> {

    private static UserMapper instance;

    private UserMapper(Class<UserModel> modelClass, Class<UserEntity> entityClass) {
        super(modelClass, entityClass);
    }

    public static UserMapper getInstance() {
        if (instance == null) {
            instance = new UserMapper(UserModel.class, UserEntity.class);
        }
        return instance;
    }
}
