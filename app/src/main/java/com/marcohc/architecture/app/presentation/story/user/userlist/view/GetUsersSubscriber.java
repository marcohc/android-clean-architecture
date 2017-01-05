package com.marcohc.architecture.app.presentation.story.user.userlist.view;

import android.support.annotation.NonNull;

import com.marcohc.architecture.app.domain.interactor.GetUsersUseCase;
import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.common.util.utils.Preconditions;
import com.marcohc.architecture.rx.domain.interactor.SimpleSubscriber;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Subscriber which gets the response from {@link GetUsersUseCase}.
 */
class GetUsersSubscriber extends SimpleSubscriber<List<UserModel>> {

    private final WeakReference<GetUsersListener> weakReference;

    GetUsersSubscriber(@NonNull GetUsersListener listener) {
        this.weakReference = new WeakReference<>(Preconditions.checkNotNull(listener));
    }

    @Override
    public void onError(Throwable throwable) {
        final GetUsersListener listener = weakReference.get();
        if (listener != null) {
            listener.onUserListFailure(throwable);
        }
    }

    @Override
    public void onNext(List<UserModel> modelList) {
        final GetUsersListener listener = weakReference.get();
        if (listener != null) {
            listener.onUserListSuccess(modelList);
        }
    }

    interface GetUsersListener {

        void onUserListSuccess(@NonNull List<UserModel> model);

        void onUserListFailure(@NonNull Throwable throwable);

    }
}