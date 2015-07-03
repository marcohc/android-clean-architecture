package com.marcohc.android.clean.architecture.data.util;

public class NetworkManager {

    public static final String LOG_TAG = "NetworkManager";

    // Determinate which kind of persistence is being used
    public static final String LOCAL_PERSISTENCE = "local_persistence";
    public static final String REMOTE_PERSISTENCE = "remote_persistence";
    public static String PERSISTENCE_MANAGER = REMOTE_PERSISTENCE;

    // Base API url
    public static String BASE_API_URL = "https://buurapp.xs2theworld.com";

    public enum SERVER_ERRORS {
        UNKNOWN, AUTHENTICATION;
    }
}
