package com.marcohc.architecture.common.util.helper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marcohc.architecture.common.util.utils.Preconditions;

import java.util.regex.Pattern;

public final class StringUtils {

    private static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static Pattern pattern;

    private StringUtils() {
    }

    /**
     * Validate hex with regular expression
     *
     * @param email hex for validation
     * @return true valid hex, false invalid hex
     */
    public static boolean isEmailValid(@NonNull final String email) {
        Preconditions.checkNotNull(email, "email");
        boolean isValid = false;
        if (pattern == null) {
            pattern = Pattern.compile(EMAIL_PATTERN);
        }
        isValid = pattern.matcher(email).matches();
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

    @NonNull
    public static String getFirstCapitalize(@NonNull String text) {
        Preconditions.checkNotNull(text, "text");
        StringBuilder textCapitalize = new StringBuilder();
        if (!text.isEmpty()) {
            textCapitalize.append(text.substring(0, 1).toUpperCase()).append(text.substring(1, text.length()));
        }
        return textCapitalize.toString();
    }

    @NonNull
    public static String getAllCapitalize(@NonNull String text) {
        Preconditions.checkNotNull(text, "text");
        StringBuilder textCapitalize = new StringBuilder();
        if (!text.isEmpty()) {
            for (int i = 0; i < text.length(); i++) {
                textCapitalize.append(String.valueOf(text.charAt(i)).toUpperCase());
            }
        }
        return textCapitalize.toString();
    }

}
