package com.marcohc.architecture.app.presentation.presenter.impl;

import android.os.AsyncTask;

import com.marcohc.architecture.app.MainApplication;
import com.marcohc.architecture.app.presentation.activity.inter.StartView;
import com.marcohc.architecture.app.presentation.presenter.inter.StartPresenter;
import com.marcohc.architecture.presentation.presenter.BasePresenter;

import timber.log.Timber;

@SuppressWarnings("ConstantConditions")
public class StartPresenterImpl extends BasePresenter<StartView> implements StartPresenter {

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onViewCreated() {
        new SetUpTask().execute();
    }

    // Show splash screen and wait until MainApplication has finished loading stuff
    private class SetUpTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {

            Timber.d("2 - StartActivity: Waiting for MainApplication to finish loading data");

            // Do your initialization stuff here

            MainApplication.waitUntilApplicationIsInitialized();

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            Timber.d("3 - StartActivity: Going to main");
            if (isViewAttached()) {
                getView().goToMain();
            }
        }
    }


}
