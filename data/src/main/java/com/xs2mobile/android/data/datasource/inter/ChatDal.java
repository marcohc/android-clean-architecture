package com.xs2mobile.buurapp.data.datasource.inter;

import com.xs2mobile.buurapp.data.datasource.rest.RepositoryCallback;

import java.util.HashMap;

public interface ChatDal {

    void getChats(String token, RepositoryCallback<Object> callback);

    void createChat(HashMap<String, String> map, String token, RepositoryCallback callback);

}
