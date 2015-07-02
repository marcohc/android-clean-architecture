package com.xs2mobile.buurapp.presentation.util;

public class NavigationManager {

    public static final String LOG_TAG = "NavigationManager";
    public static final String IS_EDITING = "is_editing";
    public static final String INITIATIVE = "initiative";
    public static final String USER = "user";
    public static final String CHAT = "chat";

    public static int lastViewPosition;
    public static int currentViewPosition;

    public enum SCREENS {
        INITIATIVES_POSITION, NEIGHBOURS_CHAT, MY_PROFILE
    }

}
