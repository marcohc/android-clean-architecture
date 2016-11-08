package com.marcohc.architecture.app.presentation.util;

import com.marcohc.architecture.app.BuildConfig;
import com.marcohc.architecture.common.helper.BaseBuildConfig;

/**
 * Methods for managing app debug information
 */
public class BuildConfigHelper extends BaseBuildConfig {

    private static BuildConfigHelper instance;

    private BuildConfigHelper(String buildType) {
        super(buildType);
    }

    public static BuildConfigHelper getInstance() {
        if (instance == null) {
            instance = new BuildConfigHelper(BuildConfig.BUILD_TYPE);
        }
        return instance;
    }
}
