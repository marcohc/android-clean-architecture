package com.marcohc.android.clean.architecture.domain.bus.request;

import com.marcohc.android.clean.architecture.common.bus.events.request.BaseRequest;

public class LogInRequest extends BaseRequest {

    private final String username;
    private final String password;

    public LogInRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
