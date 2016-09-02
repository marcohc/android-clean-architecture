/*
 * Copyright (C) 2016 Marco Hernaiz Cao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.marcohc.architecture.common.helper;


/**
 * Methods for managing app debug information.
 * <p/>
 * Extends this class and call constructor
 */
public abstract class BaseBuildConfig {

    // ************************************************************************************************************************************************************************
    // * Constants
    // ************************************************************************************************************************************************************************

    private final String DEBUG = "debug";
    private final String TEST = "appTest";
    private final String ACCEPTANCE = "acceptance";
    private final String PRODUCTION = "release";

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private String buildType;

    protected BaseBuildConfig(String buildType) {
        this.buildType = buildType;
    }

    public boolean isDevelopment() {
        return buildType.equals(DEBUG);
    }

    public boolean isTest() {
        return buildType.equals(TEST);
    }

    public boolean isAcceptance() {
        return buildType.equals(ACCEPTANCE);
    }

    public boolean isProduction() {
        return buildType.equals(PRODUCTION);
    }

    public String getBuildVariantName() {
        switch (buildType) {
            case DEBUG:
                return "Development";
            case TEST:
                return "Acceptance";
            case ACCEPTANCE:
                return "Acceptance";
            case PRODUCTION:
                return "Production";
            default:
                return "Unknown";
        }
    }
}
