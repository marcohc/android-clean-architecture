package com.marcohc.android.clean.architecture.presentation.util;


import com.marcohc.android.clean.architecture.sample.BuildConfig;

/**
 * Methods for managing app debug information
 */
public class AppConfigHelper {

    private static final String DEBUG = "debug";
    private static final String ACCEPTANCE = "acceptance";
    private static final String PRODUCTION = "release";

    public static boolean isDevelopment() {
        return BuildConfig.BUILD_TYPE.equals(DEBUG);
    }

    public static boolean isAcceptance() {
        return BuildConfig.BUILD_TYPE.equals(ACCEPTANCE);
    }

    public static boolean isProduction() {
        return BuildConfig.BUILD_TYPE.equals(PRODUCTION);
    }

    public static String getBuildVariantName() {
        switch (BuildConfig.BUILD_TYPE) {
            case DEBUG:
                return "Development";
            case ACCEPTANCE:
                return "Acceptance";
            case PRODUCTION:
                return "Production";
            default:
                return "Unknown";
        }
    }
}
