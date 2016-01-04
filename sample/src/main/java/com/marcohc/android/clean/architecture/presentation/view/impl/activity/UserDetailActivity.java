package com.marcohc.android.clean.architecture.presentation.view.impl.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.marcohc.android.clean.architecture.domain.model.UserModel;
import com.marcohc.android.clean.architecture.presentation.R;
import com.marcohc.android.clean.architecture.presentation.presenter.impl.UserDetailPresenterImpl;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.UserDetailPresenter;
import com.marcohc.android.clean.architecture.presentation.util.NavigationManager;
import com.marcohc.android.clean.architecture.presentation.view.activity.BaseMvpActivity;
import com.marcohc.android.clean.architecture.presentation.view.inter.UserDetailView;
import com.marcohc.helperoid.StringHelper;

import butterknife.Bind;

public class UserDetailActivity extends BaseMvpActivity<UserDetailView, UserDetailPresenter> implements UserDetailView {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.userImage)
    ImageView userImage;

    @Bind(R.id.usernameText)
    TextView usernameText;

    @Bind(R.id.addressText)
    TextView addressText;

    @Bind(R.id.dateOfBirthAndEmailText)
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

    // ************************************************************************************************************************************************************************
    // * View interface methods
    // ************************************************************************************************************************************************************************

    @Override
    public void setUserData(UserModel user) {
        if (!StringHelper.isEmpty(user.getPicture().getThumbnail())) {
            Glide.with(this).load(user.getPicture().getThumbnail()).into(userImage);
        }
        usernameText.setText(user.getUsername());
        addressText.setText(user.getLocation().getStreet());
        dateOfBirthAndEmailText.setText(user.getEmail());
    }

    // ************************************************************************************************************************************************************************
    // * UI methods
    // ************************************************************************************************************************************************************************


}
