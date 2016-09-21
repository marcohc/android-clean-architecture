package com.marcohc.architecture.presentation.view;

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

    void showMessage(String message);

    void showError(String message);

    // Messages from string values

    String getString(int stringId);

    String getString(int stringId, Object... formatArgs);

    String getQuantityString(int stringId, int quantity, Object... formatArgs);


}
