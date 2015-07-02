package com.xs2mobile.buurapp.presentation.view.impl.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xs2mobile.buurapp.R;
import com.xs2mobile.buurapp.presentation.view.impl.activity.ProfileDetailActivity;
import com.xs2mobile.buurapp.presentation.util.NavigationManager;
import com.xs2mobile.buurapp.presentation.model.UserModel;
import com.xs2mobile.buurapp.presentation.presenter.impl.ProfilePresenterImpl;
import com.xs2mobile.buurapp.presentation.presenter.inter.ProfilePresenter;
import com.xs2mobile.buurapp.presentation.view.inter.ProfileView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.InjectView;
import butterknife.OnClick;

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

    @OnClick(R.id.editProfileButton)
    protected void onEditProfileButtonClick() {
        presenter.onEditProfileButtonClick();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            setUserData(presenter.getUser());
        }
    }

    // ************************************************************************************************************************************************************************
    // * View interface methods
    // ************************************************************************************************************************************************************************

    @Override
    public void goToProfileDetail() {
        Intent intent = new Intent(getActivity(), ProfileDetailActivity.class);
        intent.putExtra(NavigationManager.IS_EDITING, true);
        getActivity().startActivityForResult(intent, 1);
    }

    // ************************************************************************************************************************************************************************
    // * UI methods
    // ************************************************************************************************************************************************************************

    private void setUserData(UserModel user) {
        if (user.getImage() != null && !user.getImage().isEmpty()) {
            Picasso.with(getActivity()).load(user.getImage()).into(userImage);
        }
        usernameText.setText(user.getUsername());
        addressText.setText(user.getAddress().getStreet());

        Date date;
        String birthday = user.getDateOfBirth();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = simpleDateFormat.parse(birthday);
            simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            user.setDateOfBirth(simpleDateFormat.format(date));
        } catch (ParseException ex) {
        }

        dateOfBirthAndEmailText.setText(user.getDateOfBirth() + " " + user.getEmail());
    }
}
