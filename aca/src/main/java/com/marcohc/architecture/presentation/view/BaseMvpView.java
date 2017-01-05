package com.marcohc.architecture.presentation.view;

import android.support.annotation.Nullable;
import android.support.annotation.PluralsRes;
import android.support.annotation.StringRes;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Base view class to be implemented by Activities and Fragments.
 * <p>
 * It contains basic UI methods
 *
 * @author Marco Hernaiz
 * @since 09/08/16
 */
public interface BaseMvpView extends MvpView {

    void showDialog(String title, String message, boolean isCancelable);

    void showDialog(@Nullable String message);

    void hideDialog();

    void showError(@Nullable String message);

    void showMessage(@Nullable String message);

    String getString(@StringRes int stringId);

    String getString(@StringRes int stringId, Object... formatArgs);

    String getQuantityString(@PluralsRes int stringId, int quantity, Object... formatArgs);

}
