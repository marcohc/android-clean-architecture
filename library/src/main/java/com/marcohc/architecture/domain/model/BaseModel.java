package com.marcohc.architecture.domain.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.marcohc.helperoid.ParserHelper;

import java.util.Map;

public abstract class BaseModel implements Model {

    public String toJsonString() {
        return ParserHelper.getInstance().toJsonString(this);
    }

    public Map<String, Object> toMap() {
        return ParserHelper.getInstance().parse(this, new TypeReference<Map<String, Object>>() {
        });
    }

}
