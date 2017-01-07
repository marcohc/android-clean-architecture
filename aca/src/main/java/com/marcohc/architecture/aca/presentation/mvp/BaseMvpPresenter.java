package com.marcohc.architecture.aca.presentation.mvp;

/**
 * Base presenter which contains common methods.
 * <p>
 * Override it for specific common methods in presenters.
 *
 * @param <V> the MvpView which will handle this presenter
 * @author Marco Hernaiz
 * @since 08/08/16
 */
public abstract class BaseMvpPresenter<V extends BaseMvpView> extends MvpNullObjectBasePresenter<V> {
}
