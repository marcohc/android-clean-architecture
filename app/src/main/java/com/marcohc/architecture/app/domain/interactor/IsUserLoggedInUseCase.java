package com.marcohc.architecture.app.domain.interactor;

import com.marcohc.architecture.domain.interactor.SynchronousUseCase;
import com.marcohc.architecture.app.domain.util.AuthenticationManager;

import timber.log.Timber;

public class IsUserLoggedInUseCase extends SynchronousUseCase {

    @Override
    public Boolean execute() {
        boolean isLoggedIn = AuthenticationManager.getInstance().isLoggedIn();
        Timber.d("Is user logged in: %s", isLoggedIn);
        return isLoggedIn;
    }

}
