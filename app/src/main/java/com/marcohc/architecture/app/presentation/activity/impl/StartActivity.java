package com.marcohc.architecture.app.presentation.activity.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.marcohc.architecture.app.R;
import com.marcohc.architecture.app.presentation.activity.inter.StartView;
import com.marcohc.architecture.app.presentation.presenter.impl.StartPresenterImpl;
import com.marcohc.architecture.app.presentation.presenter.inter.StartPresenter;
import com.marcohc.architecture.presentation.view.activity.BaseMvpActivity;

public class StartActivity extends BaseMvpActivity<StartView, StartPresenter> implements StartView {

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @NonNull
    @Override
    public StartPresenter createPresenter() {
        return new StartPresenterImpl();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        setUpView();
    }

    private void setUpView() {
        presenter.onViewCreated();
    }

    // ************************************************************************************************************************************************************************
    // * View interface methods
    // ************************************************************************************************************************************************************************

    @Override
    public void goToMain() {
        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
