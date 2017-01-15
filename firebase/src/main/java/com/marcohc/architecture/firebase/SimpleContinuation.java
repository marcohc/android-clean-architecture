package com.marcohc.architecture.firebase;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

public abstract class SimpleContinuation<A, B> implements Continuation<A, B> {

    public abstract B success(A result);

    @Override
    public B then(@NonNull Task<A> task) throws Exception {
        if (task.isSuccessful()) {
            return success(task.getResult());
        } else {
            throw task.getException();
        }
    }

}
