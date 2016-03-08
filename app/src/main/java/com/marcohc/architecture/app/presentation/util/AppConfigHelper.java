package com.marcohc.architecture.app.presentation.util;

import com.marcohc.architecture.app.BuildConfig;
import com.marcohc.architecture.common.helper.BaseAppConfig;

/**
 * Methods for managing app debug information
 */
public class AppConfigHelper extends BaseAppConfig {

    private static AppConfigHelper instance;

    protected AppConfigHelper(String buildType) {
        super(buildType);
    }

    public static AppConfigHelper getInstance() {
        if (instance == null) {
            instance = new AppConfigHelper(BuildConfig.BUILD_TYPE);
        }
        return instance;
    }
}
