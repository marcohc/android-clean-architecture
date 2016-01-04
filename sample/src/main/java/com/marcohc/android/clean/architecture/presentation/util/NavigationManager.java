package com.marcohc.android.clean.architecture.presentation.util;

public class NavigationManager {

    public static final String LOG_TAG = "NavigationManager";
    public static final String USER = "user";

    public static int lastViewPosition;
    public static int currentViewPosition;

    public enum SCREENS {
        USERS_LIST, TUTORIAL, MAP
    }

}
