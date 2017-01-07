package com.marcohc.architecture.app.presentation.story.user.userlist.view;

import android.support.annotation.NonNull;

import com.marcohc.architecture.app.domain.interactor.GetUsersUseCase;
import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.app.presentation.mvp.BasePresenter;
import com.marcohc.architecture.common.util.TimerUtils;

import java.util.List;

/**
 * Presenter implementation of {@link UsersListPresenter}.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
class UsersListPresenterImpl extends BasePresenter<UsersListView> implements UsersListPresenter, GetUsersSubscriber.GetUsersListener {

    //region Attributes
    private boolean mDataSourceWithPicture = false;
    private boolean mUseCache = false;
    private TimerUtils mTimer;
    private GetUsersUseCase mGetUsersUseCase;
    //endregion

    //region Presenter interface methods
    @Override
    public void onViewCreated() {
        getView().showLoadingDialog();
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
    //endregion

    //region Subscription methods
    @Override
    public void onUserListSuccess(@NonNull List<UserModel> modelList) {
        getView().hideDialog();
        getView().enableCancelButton(false);
        getView().renderModelList(modelList);
        getView().setTimeSpent(mTimer.logTotal());
    }

    @Override
    public void onUserListFailure(@NonNull Throwable throwable) {
        getView().showRefreshSpinner(false);
        getView().enableCancelButton(false);
        getView().showMessage(throwable.getMessage());
    }
    //endregion

    //region Private methods
    private void requestData(boolean useCache) {
        getView().showLoadingDialog();
        getView().enableCancelButton(true);
        mTimer = TimerUtils.getInstance("GetUsersUseCase");
        mGetUsersUseCase = new GetUsersUseCase(mDataSourceWithPicture, useCache);
        executeUseCase(mGetUsersUseCase, new GetUsersSubscriber(this));
    }

    private void requestFreshData() {
        getView().showLoadingDialog();
        getView().enableCancelButton(true);
        mTimer = TimerUtils.getInstance("GetUsersUseCase");
        mGetUsersUseCase = new GetUsersUseCase(mDataSourceWithPicture, false);
        executeUseCase(mGetUsersUseCase, new GetUsersSubscriber(this));
    }
    //endregion

}
