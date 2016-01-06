package com.marcohc.android.clean.architecture.domain.bus.response.data;

import com.marcohc.android.clean.architecture.common.bus.response.data.BaseDataResponse;

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
