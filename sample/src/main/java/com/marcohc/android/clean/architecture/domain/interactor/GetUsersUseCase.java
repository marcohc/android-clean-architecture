package com.marcohc.android.clean.architecture.domain.interactor;

import com.marcohc.android.clean.architecture.domain.bus.request.GetUsersRequest;
import com.marcohc.android.clean.architecture.domain.bus.response.data.GetUsersDataResponse;
import com.marcohc.android.clean.architecture.domain.bus.response.domain.GetUsersDomainResponse;
import com.marcohc.android.clean.architecture.domain.error.DomainError;
import com.marcohc.android.clean.architecture.domain.mapper.UserMapper;
import com.marcohc.android.clean.architecture.domain.model.UserModel;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class GetUsersUseCase extends AsynchronousUseCase {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private GetUsersDataResponse responseFromServer;

    // ************************************************************************************************************************************************************************
    // * Bus events factory methods
    // ************************************************************************************************************************************************************************

    @Override
    protected GetUsersRequest createRequest() {
        return new GetUsersRequest();
    }

    @Override
    protected GetUsersDomainResponse createResponse() {
        JSONArray usersArray = null;
        try {
            usersArray = (JSONArray) responseFromServer.getResponse().get("results");
        } catch (JSONException e) {
        }
        usersArray = clearUsersArray(usersArray);
        List<UserModel> usersList = UserMapper.getInstance().parseModelJsonArray(usersArray);
        return new GetUsersDomainResponse(usersList);
    }

    // ************************************************************************************************************************************************************************
    // * Use case execution
    // ************************************************************************************************************************************************************************

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEventAsync(GetUsersDataResponse responseFromServer) {

        this.responseFromServer = responseFromServer;
        if (!responseFromServer.hasError()) {
            post(createResponse());
            unregisterFromBus();
        } else {
            postAppError(new DomainError(responseFromServer.getError().getMessage(), responseFromServer.getError().getCode()));
        }
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
}
