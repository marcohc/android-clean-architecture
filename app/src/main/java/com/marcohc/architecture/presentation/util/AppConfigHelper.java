package com.marcohc.architecture.presentation.util;

import com.marcohc.architecture.sample.BuildConfig;
import com.marcohc.helperoid.BaseAppConfig;

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
