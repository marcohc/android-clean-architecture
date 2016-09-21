
package com.marcohc.architecture.app.data.service.user;

import com.marcohc.architecture.app.domain.entity.UserEntity;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Data source interface without pictures.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
public interface UsersWithoutPictureService {

    /**
     * Get users without picture.
     *
     * @return the observable with users without picture
     */
    @GET("users")
    Observable<List<UserEntity>> getAll();

}
