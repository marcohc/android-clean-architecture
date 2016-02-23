package com.marcohc.android.clean.architecture.domain.bus.response.data;

import com.marcohc.android.clean.architecture.common.bus.events.response.data.BaseDataResponse;

import org.json.JSONObject;

public class LogInDataResponse extends BaseDataResponse {

    private final JSONObject response;

    public LogInDataResponse(JSONObject response) {
        this.response = response;
    }

    public JSONObject getResponse() {
        return response;
    }
}
