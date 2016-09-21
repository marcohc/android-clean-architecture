package com.marcohc.architecture.app.presentation.story.start;

import android.os.AsyncTask;

import com.marcohc.architecture.app.MainApplication;
import com.marcohc.architecture.presentation.presenter.BaseMvpPresenter;

import timber.log.Timber;

@SuppressWarnings("ConstantConditions")
class StartPresenterImpl extends BaseMvpPresenter<StartView> implements StartPresenter {

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
            getView().goToMain();
        }
    }


}
