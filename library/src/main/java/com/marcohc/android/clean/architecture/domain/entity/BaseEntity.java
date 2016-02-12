package com.marcohc.android.clean.architecture.domain.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.marcohc.helperoid.ParserHelper;

import java.util.Map;

public abstract class BaseEntity implements Entity {

    public String toJsonString() {
        return ParserHelper.toJsonString(this);
    }

    public Map<String, Object> toMap() {
        return ParserHelper.parse(this, new TypeReference<Map<String, Object>>() {
        });
    }

}
