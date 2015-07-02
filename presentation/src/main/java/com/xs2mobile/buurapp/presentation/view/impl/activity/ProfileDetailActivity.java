package com.xs2mobile.buurapp.presentation.view.impl.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.marcohc.helperoid.ScreenHelper;
import com.squareup.picasso.Picasso;
import com.xs2mobile.buurapp.MainApplication;
import com.xs2mobile.buurapp.R;
import com.xs2mobile.buurapp.presentation.util.FileSystemManager;
import com.xs2mobile.buurapp.presentation.util.NavigationManager;
import com.xs2mobile.buurapp.presentation.model.UserModel;
import com.xs2mobile.buurapp.presentation.presenter.impl.ProfileDetailPresenterImpl;
import com.xs2mobile.buurapp.presentation.presenter.inter.ProfileDetailPresenter;
import com.xs2mobile.buurapp.presentation.view.inter.ProfileDetailView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.InjectView;
import butterknife.OnClick;

public class ProfileDetailActivity extends BaseMvpActivity<ProfileDetailView, ProfileDetailPresenter> implements ProfileDetailView {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    // View
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.userImage)
    ImageView userImage;

    @InjectView(R.id.usernameEditText)
    EditText usernameEditText;

    @InjectView(R.id.firstNameEditText)
    EditText firstNameEditText;

    @InjectView(R.id.lastNameEditText)
    EditText lastNameEditText;

    @InjectView(R.id.streetEditText)
    EditText streetEditText;

    @InjectView(R.id.numberEditText)
    EditText numberEditText;

    @InjectView(R.id.zipCodeEditText)
    EditText zipCodeEditText;

    @InjectView(R.id.cityEditText)
    EditText cityEditText;

    @InjectView(R.id.countryEditText)
    EditText countryEditText;

    @InjectView(R.id.birthdayEditText)
    EditText birthdayEditText;

    @InjectView(R.id.emailEditText)
    EditText emailEditText;

    // Creation

    @InjectView(R.id.createPasswordContainer)
    ViewGroup createPasswordContainer;

    @InjectView(R.id.passwordEditText)
    EditText passwordEditText;

    @InjectView(R.id.confirmPasswordEditText)
    EditText confirmPasswordEditText;


    // Modification

    @InjectView(R.id.resetPasswordContainer)
    ViewGroup resetPasswordContainer;

    @InjectView(R.id.currentPasswordEditText)
    EditText currentPasswordEditText;

    @InjectView(R.id.newPasswordEditText)
    EditText newPasswordEditText;

    @InjectView(R.id.confirmNewPasswordEditText)
    EditText confirmNewPasswordEditText;

    private boolean isEditing;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @Override
    public ProfileDetailPresenter createPresenter() {
        return new ProfileDetailPresenterImpl();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.profile_detail_activity);

        initializeComponentBehavior();
    }

    private void initializeComponentBehavior() {
        isEditing = getIntent().getBooleanExtra(NavigationManager.IS_EDITING, false);

        initializeActionBar();

        if (!isEditing) {
            setToCreation();
        } else if (isEditing) {
            setToEditing();
        }
    }

    private void initializeActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_action_logo);

        if (isEditing) {
            getSupportActionBar().setTitle(getString(R.string.edit_profile));
        } else {
            getSupportActionBar().setTitle(getString(R.string.sign_up));
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
                if (isEditing) {
                    presenter.onEditProfile();
                } else {
                    presenter.onCreateProfile();
                }
                break;
            default:
                break;
        }

        return false;
    }

    @OnClick(R.id.userImageContainer)
    protected void onAddPhotoImageClick() {
        presenter.onAddPhotoImageClick();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Log.d(NavigationManager.LOG_TAG, String.format("ProfileDetailActivity.onActivityResult: %d %d %s", requestCode, resultCode, intent != null ? intent.getExtras() : ""));
        if (resultCode == Activity.RESULT_OK) {
            presenter.onAddPhotoImageActivityResult(requestCode, intent);
        }
    }

    // ************************************************************************************************************************************************************************
    // * View interface methods
    // ************************************************************************************************************************************************************************

    @Override
    public String getUsername() {
        return usernameEditText.getText().toString();
    }

    @Override
    public String getFirsName() {
        return firstNameEditText.getText().toString();
    }

    @Override
    public String getLastName() {
        return lastNameEditText.getText().toString();
    }

    @Override
    public String getStreet() {
        return streetEditText.getText().toString();
    }

    @Override
    public String getNumber() {
        return numberEditText.getText().toString();
    }

    @Override
    public String getPostcode() {
        return zipCodeEditText.getText().toString();
    }

    @Override
    public String getCity() {
        return cityEditText.getText().toString();
    }

    @Override
    public String getCountry() {
        return countryEditText.getText().toString();
    }

    @Override
    public String getBirthday() {
        return birthdayEditText.getText().toString();
    }

    @Override
    public String getEmail() {
        return emailEditText.getText().toString();
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
    public String getConfirmNewPassword() {
        return confirmNewPasswordEditText.getText().toString();
    }

    @Override
    public String getCurrentPassword() {
        return currentPasswordEditText.getText().toString();
    }

    @Override
    public String getNewPassword() {
        return newPasswordEditText.getText().toString();
    }

    @Override
    public void loadImage(Bitmap bitmap) {
        userImage.setImageBitmap(bitmap);
    }

    @Override
    public void openImage(String path) {
        FileSystemManager.openImage(this, path);
    }

    @Override
    public void invalidateCreationPasswordContainer() {
        YoYo.with(Techniques.Shake).playOn(createPasswordContainer);
    }

    @Override
    public void invalidateModificationPasswordContainer() {
        YoYo.with(Techniques.Shake).playOn(resetPasswordContainer);
    }

    @Override
    public void invalidateUsername() {
        YoYo.with(Techniques.Shake).playOn(usernameEditText);
    }

    @Override
    public void invalidateStreet() {
        YoYo.with(Techniques.Shake).playOn(streetEditText);
    }

    @Override
    public void invalidateNumber() {
        YoYo.with(Techniques.Shake).playOn(numberEditText);
    }

    @Override
    public void invalidatePostCode() {
        YoYo.with(Techniques.Shake).playOn(zipCodeEditText);
    }

    @Override
    public void invalidateCity() {
        YoYo.with(Techniques.Shake).playOn(cityEditText);
    }

    @Override
    public void invalidateEmail() {
        YoYo.with(Techniques.Shake).playOn(emailEditText);
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
    public void invalidateBirthday() {
        YoYo.with(Techniques.Shake).playOn(birthdayEditText);
    }

    @Override
    public void goToMain() {
        setResult(RESULT_OK);
        finish();
    }

    // ************************************************************************************************************************************************************************
    // * UI methods
    // ************************************************************************************************************************************************************************

    private void setToEditing() {

        fillFormWithUserData();
        usernameEditText.setEnabled(false);
        streetEditText.setEnabled(false);
        numberEditText.setEnabled(false);
        zipCodeEditText.setEnabled(false);
        cityEditText.setEnabled(false);
        countryEditText.setEnabled(false);
        birthdayEditText.setEnabled(false);
        emailEditText.setEnabled(false);

        createPasswordContainer.setVisibility(View.GONE);
        resetPasswordContainer.setVisibility(View.VISIBLE);
    }

    private void setToCreation() {
        if (MainApplication.isDevelopment()) {
            fillFormWithFakeData();
        }

        createPasswordContainer.setVisibility(View.VISIBLE);
        resetPasswordContainer.setVisibility(View.GONE);
    }

    private void fillFormWithUserData() {
        UserModel user = presenter.getUser();
        writeOn(R.id.usernameEditText, user.getUsername());
        writeOn(R.id.firstNameEditText, user.getFirstName());
        writeOn(R.id.lastNameEditText, user.getLastName());
        writeOn(R.id.streetEditText, user.getAddress().getStreet());
        writeOn(R.id.numberEditText, user.getAddress().getNumber().toString());
        writeOn(R.id.zipCodeEditText, user.getAddress().getPostcode());
        writeOn(R.id.cityEditText, user.getAddress().getCity());
        writeOn(R.id.countryEditText, user.getAddress().getCountry());

        Date date;
        String birthday = user.getDateOfBirth();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = simpleDateFormat.parse(birthday);
            simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            writeOn(R.id.birthdayEditText, simpleDateFormat.format(date));
        } catch (ParseException ex) {
        }

        writeOn(R.id.emailEditText, user.getEmail());

        if (user.getImage() != null && !user.getImage().isEmpty()) {
            Picasso.with(this).load(user.getImage()).fit().into(userImage);
        }
    }

    private void fillFormWithFakeData() {
        typeOnWithShortPause(R.id.usernameEditText, "marco");
        typeOnWithShortPause(R.id.firstNameEditText, "MyFirstName");
        typeOnWithShortPause(R.id.lastNameEditText, "MyLastName");
        typeOnWithShortPause(R.id.streetEditText, "Paasheuvelweg 9F");
        typeOnWithShortPause(R.id.numberEditText, "9");
        typeOnWithShortPause(R.id.zipCodeEditText, "1105 BE");
        typeOnWithShortPause(R.id.cityEditText, "Amsterdam");
        typeOnWithShortPause(R.id.countryEditText, "Netherlands");
        typeOnWithShortPause(R.id.birthdayEditText, "06/02/1986");
        typeOnWithShortPause(R.id.emailEditText, "marco@gmail.com");
        typeOnWithShortPause(R.id.passwordEditText, "marco");
        typeOnWithShortPause(R.id.confirmPasswordEditText, "marco");
    }

    private void typeOnWithShortPause(int viewId, String value) {
        EditText editText = (EditText) findViewById(viewId);
        editText.setText(value);
    }

    private void writeOn(int viewId, String value) {
        EditText editText = (EditText) findViewById(viewId);
        editText.setText(value);
    }

    private void hideKeyBoard() {
        ScreenHelper.hideKeyboard(this);
    }

}

