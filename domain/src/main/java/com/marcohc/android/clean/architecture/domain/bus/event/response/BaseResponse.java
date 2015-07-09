package com.marcohc.android.clean.architecture.domain.bus.event.response;

import com.marcohc.android.clean.architecture.common.exception.DataException;
import com.marcohc.android.clean.architecture.domain.bus.event.BaseEvent;

import org.json.JSONArray;
import org.json.JSONObject;

public class BaseResponse extends BaseEvent {

    private final JSONObject response;
    private final JSONArray responseArray;
    private final DataException error;

    public BaseResponse() {
        this.error = null;
        this.response = null;
        this.responseArray = null;
    }

    public BaseResponse(JSONObject response) {
        this.error = DataException.getError(response);
        this.response = response;
        this.responseArray = null;
    }

    public BaseResponse(JSONArray responseArray) {
        this.error = null;
        this.response = null;
        this.responseArray = responseArray;
    }

    public JSONObject getResponse() {
        return response;
    }

    public boolean hasError() {
        return error != null;
    }

    public DataException getError() {
        return error;
    }

    public JSONArray getResponseArray() {
        return responseArray;
    }
}
