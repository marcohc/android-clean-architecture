package com.marcohc.android.clean.architecture.domain.mapper;

import com.marcohc.android.clean.architecture.domain.entity.UserEntity;
import com.marcohc.android.clean.architecture.domain.model.UserModel;
import com.marcohc.helperoid.ParserHelper;

import org.json.JSONArray;

import java.util.List;

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

    public List<UserModel> parseUsersList(JSONArray response) {
        return ParserHelper.parseJsonArray(response, UserModel.class);
    }
}
