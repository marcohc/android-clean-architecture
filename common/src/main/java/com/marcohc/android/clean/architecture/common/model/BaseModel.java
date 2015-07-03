package com.marcohc.android.clean.architecture.common.model;

import com.marcohc.helperoid.ParserHelper;

public abstract class BaseModel {

    public String toJsonString() {
        return ParserHelper.toJsonString(this);
    }

}
