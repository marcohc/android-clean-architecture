package com.marcohc.android.clean.architecture.domain.interactor.impl;

import com.marcohc.android.clean.architecture.domain.interactor.inter.SynchronousUseCase;

public class IsUserLoggedInUseCase extends SynchronousUseCase {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    // ************************************************************************************************************************************************************************
    // * Constructor
    // ************************************************************************************************************************************************************************

    public IsUserLoggedInUseCase() {
    }

    // ************************************************************************************************************************************************************************
    // * Use case execution
    // ************************************************************************************************************************************************************************

    public boolean execute() {
        return UserRepository.getInstance().isUserLoggedIn();
    }
}
