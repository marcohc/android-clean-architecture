package com.marcohc.android.clean.architecture.domain.bus.request;

import com.marcohc.android.clean.architecture.common.bus.events.BusEvent;

public class SignUpRequest implements BusEvent {

    private final String username;
    private final String password;

    public SignUpRequest(String username, String password) {
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
