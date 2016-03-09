package com.marcohc.architecture.domain.entity;

import java.util.Map;

public interface Entity {

    String toJsonString();

    Map<String, Object> toMap();
}
