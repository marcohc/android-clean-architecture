package com.marcohc.architecture.domain.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.marcohc.architecture.common.helper.ParserHelper;

import java.util.Map;

public class BaseJsonModel {

    public String toJsonString() {
        return ParserHelper.getInstance().toJsonString(this);
    }

    public Map<String, Object> toMap() {
        return ParserHelper.getInstance().parse(this, new TypeReference<Map<String, Object>>() {});
    }

}
