package com.marcohc.architecture.app.presentation.activity.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.marcohc.architecture.app.R;
import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.app.presentation.activity.inter.UserDetailView;
import com.marcohc.architecture.app.presentation.presenter.impl.UserDetailPresenterImpl;
import com.marcohc.architecture.app.presentation.presenter.inter.UserDetailPresenter;
import com.marcohc.architecture.app.presentation.util.NavigationManager;
import com.marcohc.architecture.common.helper.StringHelper;
import com.marcohc.architecture.presentation.view.activity.BaseMvpActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class UserDetailActivity extends BaseMvpActivity<UserDetailView, UserDetailPresenter> implements UserDetailView {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.userImage)
    ImageView userImage;

    @BindView(R.id.usernameText)
    TextView usernameText;

    @BindView(R.id.addressText)
    TextView addressText;

    @BindView(R.id.dateOfBirthAndEmailText)
    TextView dateOfBirthAndEmailText;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @NonNull
    @Override
    public UserDetailPresenter createPresenter() {
        return new UserDetailPresenterImpl();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_detail_activity);
        setUpActionBar();
        setUpUserData();
    }

    private void setUpActionBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.user_detail);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    private void setUpUserData() {
        String userJson = getIntent().getStringExtra(NavigationManager.USER);
        if (!StringHelper.isEmpty(userJson)) {
            presenter.onViewCreated(userJson);
        } else {
            showError("This activity must receive a user!");
        }
    }

    // ************************************************************************************************************************************************************************
    // * Event handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @OnClick(R.id.userImage)
    protected void onUserImageClick() {
    }

    // ************************************************************************************************************************************************************************
    // * View interface methods
    // ************************************************************************************************************************************************************************

    @Override
    public void setUserData(UserModel user) {
        usernameText.setText(user.getName());
        dateOfBirthAndEmailText.setText(user.getEmail());
    }

    // ************************************************************************************************************************************************************************
    // * UI methods
    // ************************************************************************************************************************************************************************


}
