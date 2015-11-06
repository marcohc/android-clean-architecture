package com.marcohc.android.clean.architecture.domain.bus.response.data;

import com.marcohc.android.clean.architecture.common.bus.response.data.BaseDataResponse;

public class IsFirstTimeInTheAppDataResponse extends BaseDataResponse {

    private boolean firstTimeInTheApp;

    public IsFirstTimeInTheAppDataResponse(boolean firstTimeInApp) {
        this.firstTimeInTheApp = firstTimeInApp;
    }

    public boolean isFirstTimeInTheApp() {
        return firstTimeInTheApp;
    }
}
