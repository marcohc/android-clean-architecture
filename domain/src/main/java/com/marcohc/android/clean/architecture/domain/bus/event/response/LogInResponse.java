package com.marcohc.android.clean.architecture.domain.bus.event.response;

import com.marcohc.android.clean.architecture.domain.bus.event.BaseEvent;

import org.json.JSONObject;

public class LogInResponse extends BaseEvent {

    private final JSONObject response;

    public LogInResponse(JSONObject response) {
        this.response = response;
    }

    public JSONObject getResponse() {
        return response;
    }
}
