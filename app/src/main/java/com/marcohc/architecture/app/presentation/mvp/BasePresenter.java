package com.marcohc.architecture.app.presentation.mvp;

import com.marcohc.architecture.presentation.view.BaseMvpView;
import com.marcohc.architecture.rx.presentation.mvp.BaseRxMvpPresenter;

public abstract class BasePresenter<V extends BaseMvpView> extends BaseRxMvpPresenter<V> {

}
