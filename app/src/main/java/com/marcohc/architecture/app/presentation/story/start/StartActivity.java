package com.marcohc.architecture.app.presentation.story.start;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marcohc.architecture.app.R;
import com.marcohc.architecture.app.presentation.story.main.MainActivity;
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
    protected int getLayoutId() {
        return R.layout.start_activity;
    }

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
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
