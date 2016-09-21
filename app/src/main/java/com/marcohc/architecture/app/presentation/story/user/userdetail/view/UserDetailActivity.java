package com.marcohc.architecture.app.presentation.story.user.userdetail.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.marcohc.architecture.app.R;
import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.common.helper.StringHelper;
import com.marcohc.architecture.presentation.view.activity.BaseMvpActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Activity which displays the detail of a {@link UserModel}.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
public class UserDetailActivity extends BaseMvpActivity<UserDetailView, UserDetailPresenter> implements UserDetailView {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.userImageView)
    ImageView mUserImageView;

    @BindView(R.id.usernameTextView)
    TextView mUsernameTextView;

    @BindView(R.id.emailTextView)
    TextView mEmailTextView;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @NonNull
    @Override
    public UserDetailPresenter createPresenter() {
        return new UserDetailPresenterImpl();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_detail;
    }

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        setUpActionBar();
        setUpUserData();
    }

    private void setUpActionBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.user_detail);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    private void setUpUserData() {
        presenter.onViewCreated(this);
    }

    // ************************************************************************************************************************************************************************
    // * Event handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @OnClick(R.id.userImageView)
    protected void onUserImageClick() {
    }

    // ************************************************************************************************************************************************************************
    // * View interface methods
    // ************************************************************************************************************************************************************************

    @Override
    public void renderModel(@NonNull UserModel model) {
        String image = model.getPictureUrl();
        if (!StringHelper.isBlank(image)) {
            Glide.with(this).load(image).error(R.drawable.im_user_place_holder).into(mUserImageView);
        } else {
            mUserImageView.setImageResource(R.drawable.im_user_place_holder);
        }
        mUsernameTextView.setText(model.getName());
        mEmailTextView.setText(model.getEmail());
    }

    // ************************************************************************************************************************************************************************
    // * UI methods
    // ************************************************************************************************************************************************************************


}
