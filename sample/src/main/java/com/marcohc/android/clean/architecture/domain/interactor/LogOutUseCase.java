package com.marcohc.android.clean.architecture.domain.interactor;

import com.marcohc.android.clean.architecture.domain.util.AuthenticationManager;

public class LogOutUseCase extends SynchronousUseCase {

    @Override
    public Boolean execute() {
        AuthenticationManager.getInstance().logOut();
        return true;
    }
}
