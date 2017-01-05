package com.marcohc.architecture.common.util.helper;

import android.support.annotation.Nullable;

import java.util.regex.Pattern;

public class StringHelper {

    private static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static Pattern pattern;

    /**
     * Validate hex with regular expression
     *
     * @param email hex for validation
     * @return true valid hex, false invalid hex
     */
    public static boolean isEmailValid(final String email) {
        boolean isValid = false;
        if (email != null) {
            if (pattern == null) {
                pattern = Pattern.compile(EMAIL_PATTERN);
            }
            isValid = pattern.matcher(email).matches();
        }
        return isValid;
    }

    /**
     * Check if the parameters is blank.
     *
     * @param text the text to check
     * @return true if is blank, false otherwise
     */
    public static boolean isBlank(@Nullable final CharSequence text) {
        return text == null || text.toString().trim().isEmpty();
    }

    /**
     * Check if the parameters is blank.
     *
     * @param text the text to check
     * @return true if is blank, false otherwise
     */
    public static boolean isBlank(@Nullable final String text) {
        return text == null || text.trim().isEmpty();
    }

}
