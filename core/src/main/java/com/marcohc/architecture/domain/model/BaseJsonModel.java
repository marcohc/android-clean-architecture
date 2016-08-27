package com.marcohc.architecture.domain.model;

import com.google.gson.reflect.TypeToken;
import com.marcohc.architecture.common.helper.ParserHelper;

import java.util.Map;

public class BaseJsonModel {

    public String toJsonString() {
        return ParserHelper.getInstance().toJsonString(this);
    }

    public Map<String, Object> toMap() {
        return ParserHelper.getInstance().parse(this, new TypeToken<Map<String, Object>>() {
        });
    }

}
