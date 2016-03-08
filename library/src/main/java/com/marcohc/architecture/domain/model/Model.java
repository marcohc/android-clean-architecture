package com.marcohc.architecture.domain.model;

import java.util.Map;

public interface Model {

    String toJsonString();

    Map<String, Object> toMap();
}
