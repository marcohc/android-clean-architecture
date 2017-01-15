package com.marcohc.architecture.firebase;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

public abstract class ResultContinuation<T> implements Continuation<T, T> {

    @Nullable
    @Override
    public T then(@NonNull Task<T> task) throws Exception {
        if (task.isSuccessful()) {
            success(task.getResult());
        } else {
            //noinspection ConstantConditions
            failure(task.getException());
        }
        return null;
    }

    protected abstract void success(@NonNull T result);

    protected abstract void failure(@NonNull Exception exception);

}
