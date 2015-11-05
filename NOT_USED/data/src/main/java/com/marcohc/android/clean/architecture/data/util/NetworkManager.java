package com.marcohc.android.clean.architecture.data.util;

public class NetworkManager {

    public static final String LOG_TAG = "NetworkManager";

    // Base API url
    public static String BASE_API_URL = "http://api.randomuser.me";

    public enum SERVER_ERRORS {
        UNKNOWN, AUTHENTICATION;
    }
}
