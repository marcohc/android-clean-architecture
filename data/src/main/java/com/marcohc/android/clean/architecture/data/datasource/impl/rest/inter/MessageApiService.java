
package com.marcohc.android.clean.architecture.data.datasource.impl.rest.inter;

import com.marcohc.android.clean.architecture.data.net.RepositoryCallback;
import com.marcohc.android.clean.architecture.data.entity.inter.MessageEntity;

import java.util.HashMap;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

public interface MessageApiService {

    @GET("/message/{chatId}/{lastMessageId}/")
    void get(@Path("chatId") Long chatId, @Path("lastMessageId") Long lastMessageId, RepositoryCallback<Object> callback);

    @POST("/message/")
    void create(@Body MessageEntity message, RepositoryCallback callback);

    @POST("/push/subscribe/")
    void registerToPushNotifications(@Body HashMap<String, String> map, RepositoryCallback callback);

    @PUT("/push/status/")
    void unregisterFromPushNotifications(@Body HashMap<String, Object> map, RepositoryCallback callback);
}
