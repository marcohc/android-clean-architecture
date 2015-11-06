package com.marcohc.android.clean.architecture.domain.mapper;

import com.marcohc.android.clean.architecture.domain.entity.UserEntity;
import com.marcohc.android.clean.architecture.domain.model.UserModel;
import com.marcohc.helperoid.ParserHelper;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class UserMapper extends BaseMapper {

    public static List<UserModel> parseUsersList(JSONArray response) {
        return ParserHelper.parseJsonArray(response, UserModel.class);
    }

    private static List<UserModel> getUsersList(List<? extends UserEntity> entitiesList) {
        List<UserModel> modelsList = new ArrayList<>();
        for (UserEntity item : entitiesList) {
            UserModel model = transform(item, UserModel.class);
            modelsList.add(model);
        }
        return modelsList;
    }

    public static UserModel parseUser(String jsonResponse) {
        return transform(ParserHelper.parse(jsonResponse, UserEntity.class), UserModel.class);
    }

    public static UserModel parse(UserEntity user) {
        return transform(user, UserModel.class);
    }

    public static UserEntity parse(UserModel user) {
        return transform(user, UserEntity.class);
    }
}
