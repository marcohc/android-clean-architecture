package com.xs2mobile.buurapp.presentation.presenter.inter;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.xs2mobile.buurapp.presentation.model.InitiativeModel;
import com.xs2mobile.buurapp.presentation.notification.NotificationManager;
import com.xs2mobile.buurapp.presentation.view.inter.MainView;

public interface MainPresenter extends MvpPresenter<MainView> {

    void onViewCreated();

    void onNotificationReceived(String pushId, NotificationManager.NOTIFICATION_KEY notificationKey, String messageJson);

    void onViewCreatedResponse();

    void onGetInitiativeResponse(InitiativeModel model);
}
