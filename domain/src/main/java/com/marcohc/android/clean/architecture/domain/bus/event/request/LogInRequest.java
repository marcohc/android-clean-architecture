package com.marcohc.android.clean.architecture.domain.bus.event.request;

import com.marcohc.android.clean.architecture.domain.bus.event.BaseEvent;

public class LogInRequest extends BaseEvent {

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
