package com.marcohc.architecture.app.presentation.util;

import com.marcohc.architecture.app.BuildConfig;
import com.marcohc.architecture.common.util.BaseBuildConfigUtils;

/**
 * Methods for managing app debug information
 */
public class BuildConfigUtils extends BaseBuildConfigUtils {

    private static BuildConfigUtils instance;

    private BuildConfigUtils(String buildType) {
        super(buildType);
    }

    public static BuildConfigUtils getInstance() {
        if (instance == null) {
            instance = new BuildConfigUtils(BuildConfig.BUILD_TYPE);
        }
        return instance;
    }
}
