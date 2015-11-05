package com.marcohc.android.clean.architecture.domain.entity;

import com.marcohc.helperoid.ParserHelper;

public abstract class BaseEntity {

    public String toJsonString() {
        return ParserHelper.toJsonString(this);
    }

}
