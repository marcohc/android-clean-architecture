package com.marcohc.android.clean.architecture.domain.interactor;

import com.marcohc.helperoid.AppInfoHelper;
import com.marcohc.helperoid.PreferencesHelper;

import timber.log.Timber;

public class IsFirstTimeInTheAppUseCase extends SynchronousUseCase {

    @Override
    public Boolean execute() {
        boolean isFirstAppOnTheApp = AppInfoHelper.isFirstAppExecution();
        Timber.d("Is first time in the app: %s", isFirstAppOnTheApp);
        return isFirstAppOnTheApp;
    }

}
