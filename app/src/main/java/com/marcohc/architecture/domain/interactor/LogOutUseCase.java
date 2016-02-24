package com.marcohc.architecture.domain.interactor;

import com.marcohc.architecture.domain.util.AuthenticationManager;

public class LogOutUseCase extends SynchronousUseCase {

    @Override
    public Boolean execute() {
        AuthenticationManager.getInstance().logOut();
        return true;
    }
}
