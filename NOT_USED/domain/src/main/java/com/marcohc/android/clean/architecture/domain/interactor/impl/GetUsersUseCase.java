package com.marcohc.android.clean.architecture.domain.interactor.impl;

import com.marcohc.android.clean.architecture.domain.bus.event.request.GetUsersRequest;
import com.marcohc.android.clean.architecture.domain.bus.event.response.BaseResponse;
import com.marcohc.android.clean.architecture.domain.bus.event.response.GetUsersResponse;
import com.marcohc.android.clean.architecture.domain.interactor.inter.AsynchronousUseCase;
import com.marcohc.android.clean.architecture.domain.mapper.UserMapper;
import com.marcohc.android.clean.architecture.domain.model.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class GetUsersUseCase extends AsynchronousUseCase {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private BaseResponse response;

    // ************************************************************************************************************************************************************************
    // * Bus events factory methods
    // ************************************************************************************************************************************************************************

    @Override
    protected GetUsersRequest createRequest() {
        return new GetUsersRequest();
    }

    @Override
    protected GetUsersResponse createResponse() {
        JSONArray usersArray = null;
        try {
            usersArray = (JSONArray) response.getResponse().get("results");
        } catch (JSONException e) {
        }
        usersArray = clearUsersArray(usersArray);
        List<UserModel> userModel = UserMapper.parseUsersList(usersArray);
        return new GetUsersResponse(userModel);
    }

    /**
     * Clean up the response from the server
     *
     * @param usersArray
     * @return
     */
    private JSONArray clearUsersArray(JSONArray usersArray) {

        JSONArray aux = new JSONArray();
        for (int i = 0; i < usersArray.length(); i++) {
            try {
                JSONObject json = usersArray.getJSONObject(i);
                aux.put(json.get("user"));
            } catch (JSONException ignored) {
                break;
            }
        }
        return aux;
    }

    // ************************************************************************************************************************************************************************
    // * Use case execution
    // ************************************************************************************************************************************************************************

    public void onEventBackgroundThread(BaseResponse event) {
        this.response = event;
        if (!event.hasError()) {
            post(createResponse());
            unregisterFromBus();
        } else {
            handleException(event.getError());
        }
    }
}
