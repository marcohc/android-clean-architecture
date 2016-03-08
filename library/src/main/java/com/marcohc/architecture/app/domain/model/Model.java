package com.marcohc.architecture.app.domain.model;

import java.util.Map;

public interface Model {

    String toJsonString();

    Map<String, Object> toMap();
}
