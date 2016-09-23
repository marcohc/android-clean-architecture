package com.marcohc.architecture.app.presentation.story.user.userlist.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marcohc.architecture.app.domain.interactor.GetUsersUseCase;
import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.app.presentation.mvp.BasePresenter;
import com.marcohc.architecture.common.timer.Timer;
import com.marcohc.architecture.domain.exception.DomainException;
import com.marcohc.architecture.rx.domain.interactor.BaseSubscriber;

import java.util.List;

/**
 * Presenter implementation of {@link UsersListPresenter}.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
class UsersListPresenterImpl extends BasePresenter<UsersListView> implements UsersListPresenter {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    private boolean mDataSourceWithPicture = false;
    private boolean mUseCache = false;
    private Timer mTimer;
    private GetUsersUseCase mGetUsersUseCase;

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onViewCreated() {
        showLoadingDialog();
        requestFreshData();
    }

    @Override
    public void onRefresh() {
        requestFreshData();
    }

    @Override
    public void onItemClick(UserModel model) {
        getView().goToUserDetail(model);
    }

    @Override
    public void onChildViewClick(UserModel model, int position) {
        getView().showMessage(String.format("The user image of %s at position %d has been click", model != null ? model.getName() : "null", position));
    }

    @Override
    public void onUsersDataSourceSwitchChecked(boolean checked) {
        mDataSourceWithPicture = checked;
    }

    @Override
    public void onCacheSwitchChecked(boolean isChecked) {
        mUseCache = isChecked;
    }

    @Override
    public void onRequestDataButtonClick() {
        requestData(mUseCache);
    }

    @Override
    public void onCancelButtonClick() {
        if (mGetUsersUseCase != null) {
            mGetUsersUseCase.cancel();
        }
    }

    // ************************************************************************************************************************************************************************
    // * Interactor handler methods
    // ************************************************************************************************************************************************************************

    /**
     * Subscriber which gets the response from {@link GetUsersUseCase}.
     */
    private final class UserListSubscriber extends BaseSubscriber<List<UserModel>> {
        @Override
        protected void onSuccess(@Nullable List<UserModel> domainResponse) {
            hideDialog();
            getView().enableCancelButton(false);
            getView().renderModelList(domainResponse);
            getView().setTimeSpent(mTimer.logTotal());
        }

        @Override
        protected void onFailure(@NonNull Throwable throwable) {
            handleException((DomainException) throwable);
        }
    }

    // ************************************************************************************************************************************************************************
    // * Error handling methods
    // ************************************************************************************************************************************************************************

    @Override
    public <E extends DomainException> void handleException(E exception) {
        getView().showRefreshSpinner(false);
        getView().enableCancelButton(false);
        showMessage(exception.getMessage());
        super.handleException(exception);
    }

    // ************************************************************************************************************************************************************************
    // * Auxiliary methods
    // ************************************************************************************************************************************************************************

    private void requestData(boolean useCache) {
        showLoadingDialog();
        getView().enableCancelButton(true);
        mTimer = Timer.getInstance("GetUsersUseCase");
        mGetUsersUseCase = new GetUsersUseCase(mDataSourceWithPicture, useCache);
        executeUseCase(mGetUsersUseCase, new UserListSubscriber());
    }

    private void requestFreshData() {
        showLoadingDialog();
        getView().enableCancelButton(true);
        mTimer = Timer.getInstance("GetUsersUseCase");
        mGetUsersUseCase = new GetUsersUseCase(mDataSourceWithPicture, false);
        executeUseCase(mGetUsersUseCase, new UserListSubscriber());
    }

}
