package com.marcohc.architecture.firebase.data;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.marcohc.architecture.common.util.utils.Preconditions;
import com.marcohc.architecture.firebase.domain.service.NetworkService;

import java.util.Map;

import timber.log.Timber;

public final class FirebaseManager {

    private static FirebaseDatabase firebaseInstance;
    private static DatabaseReference databaseReference;
    private static NetworkService networkService;

    private FirebaseManager() {
    }

    public static void setUp(String serverUrl, NetworkService service) {
        networkService = service;
        firebaseInstance = FirebaseDatabase.getInstance();
        firebaseInstance.setLogLevel(Logger.Level.DEBUG);
        firebaseInstance.setPersistenceEnabled(true);
        databaseReference = firebaseInstance.getReferenceFromUrl(serverUrl);
    }

    public static DatabaseReference getFirebaseInstance() {
        return databaseReference;
    }

    public static Task<DataSnapshot> get(@NonNull DatabaseReference databaseReference) {
        Timber.v("getDatabase: %s", databaseReference.toString());
        final TaskCompletionSource<DataSnapshot> taskCompletionSource = new TaskCompletionSource<>();
        Task<DataSnapshot> task = taskCompletionSource.getTask();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                taskCompletionSource.setResult(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                taskCompletionSource.setException(databaseError.toException());
            }
        });
        return task;
    }

    public static Task<DataSnapshot> get(@NonNull Query query) {
        Preconditions.checkNotNull(query);
        Timber.v("getQuery: %s", query.getRef().toString());
        final TaskCompletionSource<DataSnapshot> taskCompletionSource = new TaskCompletionSource<>();
        Task<DataSnapshot> task = taskCompletionSource.getTask();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                taskCompletionSource.setResult(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                taskCompletionSource.setException(databaseError.toException());
            }
        });
        return task;
    }

    /**
     * If the map contains already the in the key the path
     */
    public static Task<Void> saveMap(@NonNull Map<String, Object> multiMap) {
        Preconditions.checkNotNull(multiMap);
        Timber.v("saveMap: %s", multiMap.toString());
        final TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource<>();
        Task<Void> task = taskCompletionSource.getTask();
        if (networkService.isOnline()) {
            databaseReference.updateChildren(multiMap, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError != null) {
                        taskCompletionSource.setException(databaseError.toException());
                    } else {
                        taskCompletionSource.setResult(null);
                    }
                }
            });
        } else {
            databaseReference.updateChildren(multiMap);
            taskCompletionSource.setResult(null);
        }
        return task;
    }

    public static Task<Void> saveValue(@NonNull Object value, @NonNull DatabaseReference databaseReference) {
        Preconditions.checkNotNull(value);
        Preconditions.checkNotNull(databaseReference);
        Timber.v("saveValue: %s", value.toString());
        final TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource<>();
        Task<Void> task = taskCompletionSource.getTask();
        if (networkService.isOnline()) {
            databaseReference.setValue(value, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError != null) {
                        taskCompletionSource.setException(databaseError.toException());
                    } else {
                        taskCompletionSource.setResult(null);
                    }
                }
            });
        } else {
            databaseReference.setValue(value);
            taskCompletionSource.setResult(null);
        }
        return task;
    }

}
