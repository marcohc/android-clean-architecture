package com.marcohc.android.clean.architecture.domain.model;

import com.marcohc.helperoid.ParserHelper;

public abstract class BaseModel implements Model {

    protected String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String toJsonString() {
        return ParserHelper.toJsonString(this);
    }

}
