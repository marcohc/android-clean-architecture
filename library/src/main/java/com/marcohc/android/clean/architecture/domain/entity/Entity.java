package com.marcohc.android.clean.architecture.domain.entity;

import java.util.Map;

public interface Entity {

    String toJsonString();

    Map<String, Object> toMap();
}
