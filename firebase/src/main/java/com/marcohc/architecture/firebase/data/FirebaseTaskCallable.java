package com.marcohc.architecture.firebase.data;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.marcohc.architecture.common.util.utils.Preconditions;

import java.util.concurrent.Callable;

public class FirebaseTaskCallable implements Callable<Task<DataSnapshot>> {

    private DatabaseReference databaseReference;
    private String path;
    private Query query;

    public FirebaseTaskCallable(@NonNull String path) {
        this.path = Preconditions.checkNotNull(path);
    }

    public FirebaseTaskCallable(@NonNull DatabaseReference databaseReference) {
        this.databaseReference = Preconditions.checkNotNull(databaseReference);
    }

    public FirebaseTaskCallable(@NonNull Query query) {
        this.query = Preconditions.checkNotNull(query);
    }

    @Override
    public Task<DataSnapshot> call() {
        if (path != null) {
            return FirebaseManager.get(FirebaseManager.getFirebaseInstance().child(path));
        } else if (query != null) {
            return FirebaseManager.get(query);
        } else {
            return FirebaseManager.get(databaseReference);
        }
    }
}
