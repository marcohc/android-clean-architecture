package com.marcohc.architecture.analytics;

public interface AnalyticsService {

    void trackAnalyticsEvent(String screenName, String actionName, String value);

    void trackAnalyticsEvent(String screenName, String actionName);

}
