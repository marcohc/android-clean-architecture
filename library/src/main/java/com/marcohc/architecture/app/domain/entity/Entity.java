package com.marcohc.architecture.app.domain.entity;

import java.util.Map;

public interface Entity {

    String toJsonString();

    Map<String, Object> toMap();
}
