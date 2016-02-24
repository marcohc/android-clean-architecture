package com.marcohc.architecture.presentation.view.impl.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.marcohc.architecture.presentation.view.impl.fragment.TutorialImageFragment;
import com.viewpagerindicator.IconPagerAdapter;

import java.util.HashMap;

public class TutorialViewPagerAdapter extends FragmentPagerAdapter implements IconPagerAdapter {

    private static final int POSITION_1 = 0;
    private static final int POSITION_2 = 1;
    private static final int POSITION_3 = 2;
    private static final int POSITION_4 = 3;
    private static final int mCount = 4;
    private HashMap<Integer, Fragment> fragmentsMap;

    public TutorialViewPagerAdapter(FragmentManager fm) {
        super(fm);
        initializeFragments();
    }

    private void initializeFragments() {
        fragmentsMap = new HashMap<>();
        fragmentsMap.put(POSITION_1, TutorialImageFragment.getInstance(POSITION_1));
        fragmentsMap.put(POSITION_2, TutorialImageFragment.getInstance(POSITION_2));
        fragmentsMap.put(POSITION_3, TutorialImageFragment.getInstance(POSITION_3));
        fragmentsMap.put(POSITION_4, TutorialImageFragment.getInstance(POSITION_4));
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentsMap.get(position);
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public int getIconResId(int index) {
        return 0;
    }
}