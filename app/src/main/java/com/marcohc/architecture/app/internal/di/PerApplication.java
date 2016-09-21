package com.marcohc.architecture.app.internal.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Runtime scope for the entire application life cycle.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
@Scope
@Retention(RUNTIME)
public @interface PerApplication {
}
