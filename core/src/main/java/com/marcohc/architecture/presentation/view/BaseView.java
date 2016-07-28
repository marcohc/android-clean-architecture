package com.marcohc.architecture.presentation.view;

import com.marcohc.architecture.presentation.mosby.mvp.MvpView;

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

    void showSuccess(String message);

    void showInfo(String message);

    void showDelete(String message);

    void showWarning(String error);

    void showError(String error);

    // Messages from string values

    String getString(int stringId);

    String getString(int stringId, Object... formatArgs);

    String getQuantityString(int stringId, int quantity);


}
