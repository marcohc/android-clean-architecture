package com.marcohc.architecture.app.data.service.user;

import com.marcohc.architecture.app.domain.entity.UsersWithPictureEntity;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Data source interface with pictures.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
public interface UsersWithPictureService {

    /**
     * Get a lot of users with picture.
     *
     * @return the observable with users with picture
     */
    @GET("?results=2500")
    Observable<UsersWithPictureEntity> getAll();

}
