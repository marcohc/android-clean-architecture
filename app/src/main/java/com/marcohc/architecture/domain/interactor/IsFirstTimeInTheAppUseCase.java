package com.marcohc.architecture.domain.interactor;

import com.marcohc.architecture.common.helper.AppInfoHelper;

import timber.log.Timber;

public class IsFirstTimeInTheAppUseCase extends SynchronousUseCase {

    @Override
    public Boolean execute() {
        boolean isFirstAppOnTheApp = AppInfoHelper.isFirstAppExecution();
        Timber.d("Is first time in the app: %s", isFirstAppOnTheApp);
        return isFirstAppOnTheApp;
    }

}
