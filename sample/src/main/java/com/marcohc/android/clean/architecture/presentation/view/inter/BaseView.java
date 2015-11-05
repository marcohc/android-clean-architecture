package com.marcohc.android.clean.architecture.presentation.view.inter;

import com.hannesdorfmann.mosby.mvp.MvpView;

public interface BaseView extends MvpView {

    // Dialogs

    void showLoadingDialog();

    void showLoadingDialog(boolean isCancelable);

    void showDialog(String message);

    void showDialog(String message, boolean isCancelable);

    void showDialog(String title, String message);

    void showDialog(String title, String message, boolean isCancelable);

    void hideDialog();

    // Toast

    void showInfo(String message);

    void showWarning(String error);

    void showError(String error);

    // Messages from string values

    String getResourceString(int stringId);

    String getResourceString(int stringId, Object... formatArgs);
}
