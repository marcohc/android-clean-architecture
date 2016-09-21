package com.marcohc.architecture.app.presentation.story.user.list;

import com.marcohc.architecture.app.domain.bus.response.domain.GetUsersDomainResponse;
import com.marcohc.architecture.app.domain.interactor.GetUsersUseCase;
import com.marcohc.architecture.app.domain.interactor.IsUserLoggedInUseCase;
import com.marcohc.architecture.presentation.presenter.BaseMvpPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import timber.log.Timber;

@SuppressWarnings("ConstantConditions")
class UsersListPresenterImpl extends BaseMvpPresenter<UsersListView> implements UsersListPresenter {

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onViewCreated() {
        showLoadingDialog();

        // Example of using an synchronous use case
        Timber.v("Is today a good day? %s", new IsUserLoggedInUseCase().execute());

        // Using an asynchronous use case
        new GetUsersUseCase().execute();
    }

    @Override
    public void onRefresh() {
        new GetUsersUseCase().execute();
    }

    // ************************************************************************************************************************************************************************
    // * Interactor handler methods
    // ************************************************************************************************************************************************************************

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetUsersDomainResponse(GetUsersDomainResponse event) {
        hideDialog();
        getView().loadData(event.getUsersList());
    }
}
