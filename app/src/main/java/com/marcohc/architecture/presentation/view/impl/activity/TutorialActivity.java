package com.marcohc.architecture.presentation.view.impl.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;

import com.marcohc.architecture.presentation.presenter.impl.TutorialPresenterImpl;
import com.marcohc.architecture.presentation.presenter.inter.TutorialPresenter;
import com.marcohc.architecture.presentation.view.activity.BaseMvpActivity;
import com.marcohc.architecture.presentation.view.impl.adapter.TutorialViewPagerAdapter;
import com.marcohc.architecture.presentation.view.inter.TutorialView;
import com.marcohc.architecture.sample.R;
import com.viewpagerindicator.CirclePageIndicator;

import butterknife.Bind;
import butterknife.OnClick;

public class TutorialActivity extends BaseMvpActivity<TutorialView, TutorialPresenter> implements TutorialView {

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
        setUpView();
    }

    public void setUpView() {
        setUpAdapter();
    }

    private void setUpAdapter() {
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
