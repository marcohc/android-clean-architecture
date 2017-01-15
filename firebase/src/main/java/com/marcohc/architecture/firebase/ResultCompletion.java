package com.marcohc.architecture.firebase;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public abstract class ResultCompletion<T> implements OnCompleteListener<T> {

    @Override
    public void onComplete(@NonNull Task<T> task) {
        if (task.isSuccessful()) {
            success(task.getResult());
        } else {
            //noinspection ConstantConditions
            failure(task.getException());
        }
    }

    protected abstract void success(@NonNull T result);

    protected abstract void failure(@NonNull Exception exception);

}
