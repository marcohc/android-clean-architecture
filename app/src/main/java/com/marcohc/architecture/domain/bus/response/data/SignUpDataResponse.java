package com.marcohc.architecture.domain.bus.response.data;

import com.marcohc.architecture.common.bus.events.response.data.BaseDataResponse;

import org.json.JSONObject;

public class SignUpDataResponse extends BaseDataResponse {

    private final JSONObject response;

    public SignUpDataResponse(JSONObject response) {
        this.response = response;
    }

    public JSONObject getResponse() {
        return response;
    }
}
