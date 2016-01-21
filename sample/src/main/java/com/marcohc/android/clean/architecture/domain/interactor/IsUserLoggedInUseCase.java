package com.marcohc.android.clean.architecture.domain.interactor;

import com.marcohc.android.clean.architecture.domain.util.AuthenticationManager;

import timber.log.Timber;

public class IsUserLoggedInUseCase extends SynchronousUseCase {

    @Override
    public Boolean execute() {
        boolean isLoggedIn = AuthenticationManager.getInstance().isLoggedIn();
        Timber.d("Is user logged in: %s", isLoggedIn);
        return isLoggedIn;
    }

}
