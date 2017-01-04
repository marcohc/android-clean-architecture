package com.marcohc.architecture.common.service.preference;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface PreferencesService {

    void clear();

    boolean getBoolean(@NonNull String key, boolean defaultValue);

    float getFloat(@NonNull String key, float defaultValue);

    int getInt(@NonNull String key, int defaultValue);

    long getLong(@NonNull String key, long defaultValue);

    @Nullable
    String getString(@NonNull String key, String defaultValue);

    void putBoolean(@NonNull String key, boolean value);

    void putFloat(@NonNull String key, float value);

    void putInt(@NonNull String key, int value);

    void putLong(@NonNull String key, long value);

    void putString(@NonNull String key, String value);

    void remove(@NonNull String key);
}
