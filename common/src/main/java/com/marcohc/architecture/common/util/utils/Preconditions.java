package com.marcohc.architecture.common.util.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marcohc.architecture.common.util.helper.StringHelper;

/**
 * An adaptation of Guava's {@code com.google.common.base.Preconditions} that is specially tailored
 * to support checks applied in Dagger's generated code.
 */
public final class Preconditions {

    private Preconditions() {
        // no-op
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     *
     * @param reference an object reference
     * @return the non-null reference that was validated
     * @throws NullPointerException if {@code reference} is null
     */
    @NonNull
    public static <T> T checkNotNull(@Nullable T reference) {
        if (reference == null) {
            final NullPointerException exception = new NullPointerException();

            exception.setStackTrace(getStackTrace(exception));

            throw exception;
        }
        return reference;
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     *
     * @param reference an object reference
     * @param name the variable name
     * @return the non-null reference that was validated
     * @throws NullPointerException if {@code reference} is null
     */
    @NonNull
    public static <T> T checkNotNull(@Nullable T reference, String name) {
        if (reference == null) {
            final NullPointerException exception = new NullPointerException(name + " must not be null.");

            exception.setStackTrace(getStackTrace(exception));

            throw exception;
        }
        return reference;
    }

    /**
     * Ensures the truth of an expression involving one or more parameters to the calling method.
     *
     * @param expression a boolean expression
     * @throws IllegalArgumentException if {@code expression} is false
     */
    public static void checkArgument(boolean expression) {
        if (!expression) {
            final IllegalArgumentException exception = new IllegalArgumentException();

            exception.setStackTrace(getStackTrace(exception));

            throw exception;
        }
    }

    /**
     * Ensures the truth of an expression involving one or more parameters to the calling method.
     *
     * @param expression a boolean expression
     * @param name the variable name
     * @throws IllegalArgumentException if {@code expression} is false
     */
    public static void checkArgument(boolean expression, @Nullable String name) {
        if (!expression) {
            final IllegalArgumentException exception = new IllegalArgumentException(name + " must not be false.");

            exception.setStackTrace(getStackTrace(exception));

            throw exception;
        }
    }

    /**
     * Ensures not blank in parameters.
     *
     * @param value the value to check
     * @param name the name you want to display in the logs
     * @param <T> the type of the value
     * @return the value you want to check
     */
    @NonNull
    public static <T extends CharSequence> T nonBlank(@Nullable T value, String name) {
        if (StringHelper.isBlank(value)) {
            final IllegalArgumentException exception = new IllegalArgumentException(name + " must not be blank.");

            exception.setStackTrace(getStackTrace(exception));

            throw exception;
        }
        return value;
    }

    @NonNull
    private static StackTraceElement[] getStackTrace(@NonNull Exception exception) {
        final StackTraceElement[] sourceStack = exception.getStackTrace();

        final StackTraceElement[] updatedStack = new StackTraceElement[sourceStack.length - 1];
        System.arraycopy(sourceStack, 1, updatedStack, 0, updatedStack.length);

        return updatedStack;
    }
}
