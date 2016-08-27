package com.marcohc.architecture.app.domain.interactor;

import com.marcohc.architecture.domain.interactor.SynchronousUseCase;

import java.util.Random;

public class IsUserLoggedInUseCase extends SynchronousUseCase {

    @Override
    public Boolean execute() {
        return new Random().nextBoolean();
    }

}
