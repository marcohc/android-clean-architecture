package com.marcohc.architecture.domain.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.marcohc.architecture.common.helper.ParserHelper;

import java.util.Map;

public abstract class BaseEntity implements Entity {

    public String toJsonString() {
        return ParserHelper.getInstance().toJsonString(this);
    }

    public Map<String, Object> toMap() {
        return ParserHelper.getInstance().parse(this, new TypeReference<Map<String, Object>>() {
        });
    }

}
