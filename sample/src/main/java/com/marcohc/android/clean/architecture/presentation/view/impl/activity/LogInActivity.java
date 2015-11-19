package com.marcohc.android.clean.architecture.presentation.view.impl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.marcohc.android.clean.architecture.presentation.R;
import com.marcohc.android.clean.architecture.presentation.presenter.impl.LogInPresenterImpl;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.LogInPresenter;
import com.marcohc.android.clean.architecture.presentation.view.activity.BaseMvpActivity;
import com.marcohc.android.clean.architecture.presentation.view.inter.LogInView;
import com.marcohc.helperoid.ScreenHelper;

import butterknife.Bind;
import butterknife.OnClick;

public class LogInActivity extends BaseMvpActivity<LogInView, LogInPresenter> implements LogInView {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    // View
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.usernameEditText)
    EditText usernameEditText;

    @Bind(R.id.passwordEditText)
    EditText passwordEditText;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @Override
    public LogInPresenter createPresenter() {
        return new LogInPresenterImpl();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_activity);

        initializeActionBar();

    }

    private void initializeActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItem1 = menu.findItem(R.id.item_1);
        menuItem1.setVisible(false);
        menuItem1.setEnabled(false);
        MenuItem menuItem2 = menu.findItem(R.id.item_2);
        menuItem2.setVisible(true);
        menuItem2.setEnabled(true);
        menuItem2.setIcon(R.drawable.abc_ic_go_search_api_mtrl_alpha);
        menuItem2.setTitle(R.string.accept);
        return true;
    }

    // ************************************************************************************************************************************************************************
    // * Event handler methods
    // ************************************************************************************************************************************************************************

    @OnClick(R.id.tipText)
    protected void onTipTextClick() {
        usernameEditText.setText("admin");
        passwordEditText.setText("admin");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        hideKeyBoard();

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.item_2:
                presenter.onActionDoneClick();
                break;
            default:
                break;
        }

        return false;
    }

    // ************************************************************************************************************************************************************************
    // * View interface methods
    // ************************************************************************************************************************************************************************

    @Override
    public String getUsername() {
        return usernameEditText.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordEditText.getText().toString();
    }

    @Override
    public void invalidateUsername() {
        YoYo.with(Techniques.Shake).playOn(usernameEditText);
    }

    @Override
    public void invalidatePassword() {
        YoYo.with(Techniques.Shake).playOn(passwordEditText);
    }

    @Override
    public void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    // ************************************************************************************************************************************************************************
    // * UI methods
    // ************************************************************************************************************************************************************************

    private void hideKeyBoard() {
        ScreenHelper.hideKeyboard(this);
    }

}