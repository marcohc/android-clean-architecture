package com.marcohc.architecture.presentation.view.impl.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import com.marcohc.architecture.sample.R;
import com.marcohc.architecture.presentation.presenter.impl.TermsAndConditionsPresenterImpl;
import com.marcohc.architecture.presentation.presenter.inter.TermsAndConditionsPresenter;
import com.marcohc.architecture.presentation.view.activity.BaseMvpActivity;
import com.marcohc.architecture.presentation.view.inter.TermsAndConditionsView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import butterknife.Bind;

public class TermsAndConditionsActivity extends BaseMvpActivity<TermsAndConditionsView, TermsAndConditionsPresenter> implements TermsAndConditionsView {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    // View
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.termsAndConditionsText)
    TextView termsAndConditionsText;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @NonNull
    @Override
    public TermsAndConditionsPresenter createPresenter() {
        return new TermsAndConditionsPresenterImpl();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms_and_conditions_activity);
        setUpActionBar();
        initializeText();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    private void setUpActionBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.terms_and_conditions);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    private void initializeText() {
        String totalString = "";
        String receiveString;
        InputStream inputStream = getResources().openRawResource(R.raw.terms_and_conditions);
        try {
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();
                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                totalString = stringBuilder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        termsAndConditionsText.setMovementMethod(new ScrollingMovementMethod());
        termsAndConditionsText.setText(Html.fromHtml(totalString));
    }

    // ************************************************************************************************************************************************************************
    // * BusEvent handler methods
    // ************************************************************************************************************************************************************************


    // ************************************************************************************************************************************************************************
    // * View interface methods
    // ************************************************************************************************************************************************************************

}
