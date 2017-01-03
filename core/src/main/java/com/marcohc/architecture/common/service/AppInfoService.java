package com.marcohc.architecture.common.service;

public interface AppInfoService {

    void forceFirstAppExecution();

    boolean isFirstAppExecution();

    boolean isFirstUseLast24Hours();

    boolean isFirstUseToday();

    void trackLastAppExecution();
}
