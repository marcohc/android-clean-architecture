package com.marcohc.android.clean.architecture.presentation.view.impl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.marcohc.android.clean.architecture.MainApplication;
import com.marcohc.android.clean.architecture.sample.R;
import com.marcohc.android.clean.architecture.presentation.presenter.impl.SignUpPresenterImpl;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.SignUpPresenter;
import com.marcohc.android.clean.architecture.presentation.view.activity.BaseMvpActivity;
import com.marcohc.android.clean.architecture.presentation.view.inter.SignUpView;
import com.marcohc.helperoid.ScreenHelper;

import butterknife.Bind;
import butterknife.OnClick;

public class SignUpActivity extends BaseMvpActivity<SignUpView, SignUpPresenter> implements SignUpView {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    // View
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.firstNameEditText)
    EditText firstNameEditText;

    @Bind(R.id.lastNameEditText)
    EditText lastNameEditText;

    @Bind(R.id.emailEditText)
    EditText usernameEditText;

    @Bind(R.id.passwordEditText)
    EditText passwordEditText;

    @Bind(R.id.confirmPasswordEditText)
    EditText confirmPasswordEditText;

    // Class
    private int timesTapped = 1;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @NonNull
    @Override
    public SignUpPresenter createPresenter() {
        return new SignUpPresenterImpl();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
        setUpActionBar();
    }

    private void setUpActionBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
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
    // * BusEvent handler methods
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

    // ************************************************************************************************************************************************************************
    // * View interface methods
    // ************************************************************************************************************************************************************************

    @OnClick(R.id.parentPanel)
    protected void onParentPanelClick() {
        if (!MainApplication.isProduction()) {
            if (timesTapped++ == 5) {
                timesTapped = 1;
                fillFormWithFakeData();
            }
        }
    }

    @Override
    public String getFirstName() {
        return firstNameEditText.getText().toString();
    }

    @Override
    public String getLastName() {
        return lastNameEditText.getText().toString();
    }

    @Override
    public String getEmail() {
        return usernameEditText.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordEditText.getText().toString();
    }

    @Override
    public String getConfirmPassword() {
        return confirmPasswordEditText.getText().toString();
    }

    @Override
    public void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void invalidateFirstName() {
        YoYo.with(Techniques.Shake).playOn(firstNameEditText);
    }

    @Override
    public void invalidateLastName() {
        YoYo.with(Techniques.Shake).playOn(lastNameEditText);
    }

    @Override
    public void invalidateEmail() {
        YoYo.with(Techniques.Shake).playOn(usernameEditText);
    }

    @Override
    public void invalidatePassword() {
        YoYo.with(Techniques.Shake).playOn(passwordEditText);
    }

    @Override
    public void invalidateConfirmPassword() {
        YoYo.with(Techniques.Shake).playOn(confirmPasswordEditText);
    }

    // ************************************************************************************************************************************************************************
    // * UI methods
    // ************************************************************************************************************************************************************************

    private void fillFormWithFakeData() {
        typeOnWithShortPause(R.id.firstNameEditText, "MyFirstName");
        typeOnWithShortPause(R.id.lastNameEditText, "MyLastName");
        typeOnWithShortPause(R.id.emailEditText, "test@test.com");
        typeOnWithShortPause(R.id.passwordEditText, "test");
        typeOnWithShortPause(R.id.confirmPasswordEditText, "test");
    }

    private void typeOnWithShortPause(int viewId, String value) {
        EditText editText = (EditText) findViewById(viewId);
        editText.setText(value);
    }

    private void hideKeyBoard() {
        ScreenHelper.hideKeyboard(this);
    }
}
