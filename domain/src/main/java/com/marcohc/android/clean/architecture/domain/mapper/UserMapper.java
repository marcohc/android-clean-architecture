package com.marcohc.android.clean.architecture.domain.mapper;

import com.marcohc.android.clean.architecture.domain.entity.impl.UserEntityImpl;
import com.marcohc.android.clean.architecture.domain.entity.inter.UserEntity;
import com.marcohc.android.clean.architecture.domain.model.UserModel;
import com.marcohc.helperoid.ParserHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserMapper extends BaseMapper {

    public static List<UserModel> parseUsersList(JSONArray response) {
        return ParserHelper.parseJsonArray(response, UserModel.class);
    }

    private static List<UserModel> getUsersList(List<? extends UserEntity> entitiesList) {
        List<UserModel> modelsList = new ArrayList<>();
        for (UserEntity item : entitiesList) {
            modelsList.add(transform(item, UserModel.class));
        }
        return modelsList;
    }

    public static UserModel parseUser(JSONObject jsonResponse) {
        return transform(ParserHelper.parseJson(jsonResponse, UserEntityImpl.class));
    }

    public static UserModel transform(UserEntity entity) {
        return transform(entity, UserModel.class);
    }
}