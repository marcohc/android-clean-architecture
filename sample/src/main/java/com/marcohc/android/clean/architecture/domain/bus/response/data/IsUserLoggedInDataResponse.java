package com.marcohc.android.clean.architecture.domain.bus.response.data;

import com.marcohc.android.clean.architecture.common.bus.response.data.BaseDataResponse;

public class IsUserLoggedInDataResponse extends BaseDataResponse {

    private Boolean isUserLoggedIn;

    public IsUserLoggedInDataResponse(boolean isUserLoggedIn) {
        this.isUserLoggedIn = isUserLoggedIn;
    }

    public Boolean isUserLoggedIn() {
        return isUserLoggedIn;
    }

    public void setUserLoggedIn(Boolean userLoggedIn) {
        this.isUserLoggedIn = userLoggedIn;
    }
}
