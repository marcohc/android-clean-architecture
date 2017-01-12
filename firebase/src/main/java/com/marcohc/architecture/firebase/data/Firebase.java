package com.marcohc.architecture.firebase.data;

import android.content.Context;
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

import java.util.Map;

import timber.log.Timber;

public final class Firebase {

    private static DatabaseReference databaseReference;
    private static NetworkService networkService;

    private Firebase() {
    }

    public static void setUp(@NonNull String serverUrl, @NonNull Context context) {
        Preconditions.checkNotNull(serverUrl, "serverUrl");
        Preconditions.checkNotNull(context, "context");
        networkService = new NetworkServiceImpl(context);
        FirebaseDatabase firebaseInstance = FirebaseDatabase.getInstance();
        firebaseInstance.setLogLevel(Logger.Level.DEBUG);
        firebaseInstance.setPersistenceEnabled(true);
        databaseReference = firebaseInstance.getReferenceFromUrl(serverUrl);
    }

    public static String generateKey(@NonNull String path) {
        checkInitialization();
        Preconditions.checkNotNull(path, "path");
        Timber.v("generateKey: %s", path);
        return databaseReference.child(path).push().getKey();
    }

    public static void keepSync(@NonNull String path, boolean keepSynced) {
        checkInitialization();
        Preconditions.checkNotNull(path, "path");
        Timber.v("keepSync: %s", path);
        databaseReference.child(path).keepSynced(keepSynced);
    }

    private static void checkInitialization() {
        if (databaseReference == null) {
            throw new ExceptionInInitializerError("You must call setUp method first!");
        }
    }

    public static Task<DataSnapshot> get(@NonNull String path) {
        checkInitialization();
        Preconditions.checkNotNull(path, "path");
        return get(databaseReference.child(path));
    }

    private static Task<DataSnapshot> get(@NonNull DatabaseReference databaseReference) {
        checkInitialization();
        Preconditions.checkNotNull(databaseReference, "databaseReference");
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

    public static Query createQuery(@NonNull String path) {
        checkInitialization();
        Preconditions.checkNotNull(path, "path");
        return databaseReference.child(path);
    }

    private static Task<DataSnapshot> get(@NonNull Query query) {
        checkInitialization();
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
        checkInitialization();
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

    public static Task<Void> saveValue(@NonNull Object value, @NonNull String path) {
        checkInitialization();
        Preconditions.checkNotNull(value);
        Preconditions.checkNotNull(path);
        return saveValue(value, databaseReference.child(path));
    }

    private static Task<Void> saveValue(@NonNull Object value, @NonNull DatabaseReference databaseReference) {
        checkInitialization();
        Preconditions.checkNotNull(value, "value");
        Preconditions.checkNotNull(databaseReference, "databaseReference");
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
