package com.marcohc.architecture.presentation.view.impl.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.marcohc.architecture.presentation.presenter.impl.ReportPresenterImpl;
import com.marcohc.architecture.presentation.presenter.inter.ReportPresenter;
import com.marcohc.architecture.presentation.util.AppConfigHelper;
import com.marcohc.architecture.presentation.view.activity.BaseMvpActivity;
import com.marcohc.architecture.presentation.view.inter.ReportView;
import com.marcohc.architecture.sample.R;
import com.marcohc.architecture.common.helper.ScreenHelper;

import butterknife.Bind;
import butterknife.OnClick;

public class ReportActivity extends BaseMvpActivity<ReportView, ReportPresenter> implements ReportView {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    // View
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.descriptionText)
    EditText descriptionText;

    @Bind(R.id.reportSpinner)
    Spinner reportSpinner;

    // Class
    private int timesTapped = 1;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @NonNull
    @Override
    public ReportPresenter createPresenter() {
        return new ReportPresenterImpl();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_activity);
        setUpView();
    }

    private void setUpView() {
        setUpActionBar();
        setUpSpinnerAdapter();
    }

    private void setUpActionBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    private void setUpSpinnerAdapter() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.report_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reportSpinner.setAdapter(adapter);
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
                onActionButtonClick();
                break;
            default:
                break;
        }

        return false;
    }

    private void onActionButtonClick() {
        String deviceValues = String.format("{brand: %s, model: %s, androidVersion: %s}", Build.BRAND, Build.MODEL, Build.VERSION.RELEASE);
        presenter.onActionDoneClick(deviceValues);
    }

    // ************************************************************************************************************************************************************************
    // * View interface methods
    // ************************************************************************************************************************************************************************

    @OnClick(R.id.parentPanel)
    protected void onParentPanelClick() {
        if (!AppConfigHelper.getInstance().isProduction()) {
            if (timesTapped++ == 5) {
                timesTapped = 1;
                fillFormWithFakeData();
            }
        }
    }

    @Override
    public String getDescriptionText() {
        return descriptionText.getText().toString();
    }

    @Override
    public String getReportText() {
        return reportSpinner.getSelectedItemPosition() != 0 ? reportSpinner.getSelectedItem().toString() : null;
    }

    @Override
    public void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void invalidateDescription() {
        YoYo.with(Techniques.Shake).playOn(descriptionText);
    }

    @Override
    public void invalidateReportSpinner() {
        YoYo.with(Techniques.Shake).playOn(reportSpinner);
    }

    // ************************************************************************************************************************************************************************
    // * UI methods
    // ************************************************************************************************************************************************************************

    private void fillFormWithFakeData() {
        reportSpinner.setSelection(1);
        typeOnWithShortPause(R.id.descriptionText, "Hi, I had this awful problem when I was using the app, it's about bla, bla, bla...");
    }

    private void typeOnWithShortPause(int viewId, String value) {
        EditText editText = (EditText) findViewById(viewId);
        editText.setText(value);
    }

    private void hideKeyBoard() {
        ScreenHelper.hideKeyboard(this);
    }
}
