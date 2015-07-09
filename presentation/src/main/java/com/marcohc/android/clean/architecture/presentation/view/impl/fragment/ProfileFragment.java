package com.marcohc.android.clean.architecture.presentation.view.impl.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.marcohc.android.clean.architecture.domain.model.UserModel;
import com.marcohc.android.clean.architecture.presentation.R;
import com.marcohc.android.clean.architecture.presentation.presenter.impl.ProfilePresenterImpl;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.ProfilePresenter;
import com.marcohc.android.clean.architecture.presentation.view.inter.ProfileView;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

import butterknife.InjectView;

public class ProfileFragment extends BaseMvpFragment<ProfileView, ProfilePresenter> implements ProfileView {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************
    @InjectView(R.id.userImage)
    ImageView userImage;

    @InjectView(R.id.usernameText)
    TextView usernameText;

    @InjectView(R.id.addressText)
    TextView addressText;

    @InjectView(R.id.dateOfBirthAndEmailText)
    TextView dateOfBirthAndEmailText;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @Override
    protected int getLayoutRes() {
        return R.layout.profile_fragment;
    }

    @Override
    public ProfilePresenter createPresenter() {
        return new ProfilePresenterImpl();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        setUserData(presenter.getUser());
    }

    // ************************************************************************************************************************************************************************
    // * Event handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            setUserData(presenter.getUser());
        }
    }

    // ************************************************************************************************************************************************************************
    // * View interface methods
    // ************************************************************************************************************************************************************************


    // ************************************************************************************************************************************************************************
    // * UI methods
    // ************************************************************************************************************************************************************************

    private void setUserData(UserModel user) {
        if (!StringUtils.isBlank(user.getPicture().getThumbnail())) {
            Picasso.with(getActivity()).load(user.getPicture().getThumbnail()).into(userImage);
        }
        usernameText.setText(user.getUsername());
        addressText.setText(user.getLocation().getStreet());
        dateOfBirthAndEmailText.setText(user.getEmail());
    }
}
