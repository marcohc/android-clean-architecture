package com.marcohc.architecture.aca.domain.model;

import android.support.annotation.Nullable;

import com.google.gson.reflect.TypeToken;
import com.marcohc.architecture.parser.Parser;

import java.util.Map;

public class BaseJsonModel {

    @Nullable
    public String toJsonString() {
        return Parser.toJsonString(this);
    }

    @Nullable
    public Map<String, Object> toMap() {
        return Parser.parse(this, new TypeToken<Map<String, Object>>() {
        });
    }

}
