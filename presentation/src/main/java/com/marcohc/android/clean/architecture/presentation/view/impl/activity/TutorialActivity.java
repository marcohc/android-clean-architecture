package com.marcohc.android.clean.architecture.presentation.view.impl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.marcohc.android.clean.architecture.presentation.R;
import com.marcohc.android.clean.architecture.presentation.view.impl.adapter.TutorialViewPagerAdapter;
import com.viewpagerindicator.CirclePageIndicator;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TutorialActivity extends AppCompatActivity {

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
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
