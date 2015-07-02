package com.xs2mobile.buurapp.data.util;

public class NetworkManager {

    public static final String LOG_TAG = "NetworkManager";
    public static final String LOCAL_PERSISTENCE = "local_persistence";
    public static final String REMOTE_PERSISTENCE = "remote_persistence";
    public static String PERSISTENCE_MANAGER = REMOTE_PERSISTENCE;
    public static String BASE_API_URL = "https://buurapp.xs2theworld.com";

    public enum ERRORS {
        UNKNOWN, AUTHENTICATION;
    }
}
