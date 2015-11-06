package com.marcohc.android.clean.architecture.presentation.view.impl.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;

import com.marcohc.android.clean.architecture.presentation.R;
import com.marcohc.android.clean.architecture.presentation.presenter.impl.TutorialPresenterImpl;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.TutorialPresenter;
import com.marcohc.android.clean.architecture.presentation.view.activity.BaseMvpActivity;
import com.marcohc.android.clean.architecture.presentation.view.impl.adapter.TutorialViewPagerAdapter;
import com.marcohc.android.clean.architecture.presentation.view.inter.TutorialView;
import com.viewpagerindicator.CirclePageIndicator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TutorialActivity extends BaseMvpActivity<TutorialView, TutorialPresenter> implements TutorialView {


    public static final int REQUEST_CODE = 1;

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    // View
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    @Bind(R.id.pageIndicator)
    CirclePageIndicator pageIndicator;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @NonNull
    @Override
    public TutorialPresenter createPresenter() {
        return new TutorialPresenterImpl();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.tutorial_activity);

        ButterKnife.bind(this);

        initializeComponentBehavior();
    }

    public void initializeComponentBehavior() {
        initializeViewPager();
    }

    private void initializeViewPager() {
        TutorialViewPagerAdapter viewPagerAdapter = new TutorialViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        pageIndicator.setViewPager(viewPager);
        viewPager.setCurrentItem(0);
    }

    // ************************************************************************************************************************************************************************
    // * Event handler methods
    // ************************************************************************************************************************************************************************

    @OnClick(R.id.skipButton)
    protected void onSkipButtonClick() {
        setResult(RESULT_OK);
        finish();
    }

    // ************************************************************************************************************************************************************************
    // * UI management methods
    // ************************************************************************************************************************************************************************

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
