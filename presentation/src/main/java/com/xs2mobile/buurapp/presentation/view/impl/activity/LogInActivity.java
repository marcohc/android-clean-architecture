package com.xs2mobile.buurapp.presentation.view.impl.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.marcohc.helperoid.ScreenHelper;
import com.xs2mobile.buurapp.MainApplication;
import com.xs2mobile.buurapp.R;
import com.xs2mobile.buurapp.presentation.presenter.impl.LogInPresenterImpl;
import com.xs2mobile.buurapp.presentation.presenter.inter.LogInPresenter;
import com.xs2mobile.buurapp.presentation.view.inter.LogInView;

import butterknife.InjectView;
import butterknife.OnClick;

public class LogInActivity extends BaseMvpActivity<LogInView, LogInPresenter> implements LogInView {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    // View
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.usernameEditText)
    EditText usernameEditText;

    @InjectView(R.id.passwordEditText)
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

        if (MainApplication.isDevelopment()) {
            fillFormWithFakeData();
        }
    }

    private void fillFormWithFakeData() {
        typeOnWithShortPause(R.id.usernameEditText, "Marco");
        typeOnWithShortPause(R.id.passwordEditText, "a");
    }

    private void typeOnWithShortPause(int viewId, String value) {
        EditText editText = (EditText) findViewById(viewId);
        editText.setText(value);
    }

    private void initializeActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_action_logo);
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
        menuItem2.setIcon(R.drawable.ic_action_done);
        menuItem2.setTitle(R.string.accept);
        return true;
    }

    // ************************************************************************************************************************************************************************
    // * Event handler methods
    // ************************************************************************************************************************************************************************

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

    @OnClick(R.id.forgottenPasswordText)
    protected void onForgottenPasswordTextClick() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.forgot_password_url)));
        startActivity(browserIntent);
//        presenter.onForgotPasswordTextClick();
    }

    // ************************************************************************************************************************************************************************
    // * View interface methods
    // ************************************************************************************************************************************************************************

    @Override
    public void goToForgottenPassword() {
        Intent intent = new Intent(this, ForgottenPasswordMvpActivity.class);
        startActivity(intent);
    }

    @Override
    public String getUsername() {
        return usernameEditText.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordEditText.getText().toString();
    }

    @Override
    public void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void invalidateUsername() {
        YoYo.with(Techniques.Shake).playOn(usernameEditText);
    }

    @Override
    public void invalidatePassword() {
        YoYo.with(Techniques.Shake).playOn(passwordEditText);
    }

    // ************************************************************************************************************************************************************************
    // * UI methods
    // ************************************************************************************************************************************************************************

    private void hideKeyBoard() {
        ScreenHelper.hideKeyboard(this);
    }
}
