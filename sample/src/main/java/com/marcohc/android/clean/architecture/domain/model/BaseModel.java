package com.marcohc.android.clean.architecture.domain.model;

import com.marcohc.helperoid.ParserHelper;

public abstract class BaseModel {

    public String toJsonString() {
        return ParserHelper.toJsonString(this);
    }

}
