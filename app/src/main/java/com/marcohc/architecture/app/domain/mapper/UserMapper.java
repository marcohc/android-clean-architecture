package com.marcohc.architecture.app.domain.mapper;

import com.marcohc.architecture.app.domain.entity.UserEntity;
import com.marcohc.architecture.app.domain.entity.UsersWithPictureEntity;
import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.aca.domain.mapper.BaseMapper;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * User mapper.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
public final class UserMapper extends BaseMapper<UserModel, UserEntity> {

    private static UserMapper sInstance;

    private UserMapper(Class<UserModel> modelClass, Class<UserEntity> entityClass) {
        super(modelClass, entityClass);
    }

    /**
     * Get singleton instance.
     *
     * @return UserMapper
     */
    public synchronized static UserMapper getInstance() {
        if (sInstance == null) {
            sInstance = new UserMapper(UserModel.class, UserEntity.class);
        }
        return sInstance;
    }

    /**
     * Parse this specific response.
     *
     * @param responseEntity the response
     * @return the adapted response
     */
    public List<UserEntity> parseResponse(UsersWithPictureEntity responseEntity) {
        List<UserEntity> userEntityList = new ArrayList<>();
        if (responseEntity != null && responseEntity.getUserWithPicturesList() != null) {
            for (UsersWithPictureEntity.UserWithPicture userWithPicture : responseEntity.getUserWithPicturesList()) {
                UserEntity entity = new UserEntity();
                try {
                    entity.setName(userWithPicture.getName().getFirst());
                    entity.setEmail(userWithPicture.getEmail());
                    entity.setPictureUrl(userWithPicture.getPicture().getUrl());
                    userEntityList.add(entity);
                } catch (NullPointerException e) {
                    Timber.e("Error when parsing error response: " + e.getMessage());
                }
            }
        }
        return userEntityList;
    }
}
