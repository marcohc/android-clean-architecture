package com.marcohc.android.clean.architecture.presentation.view.impl.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.marcohc.android.clean.architecture.presentation.R;
import com.marcohc.android.clean.architecture.presentation.notification.NotificationManager;
import com.marcohc.android.clean.architecture.presentation.presenter.impl.MainPresenterImpl;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.MainPresenter;
import com.marcohc.android.clean.architecture.presentation.util.NavigationManager;
import com.marcohc.android.clean.architecture.presentation.util.PreferencesManager;
import com.marcohc.android.clean.architecture.presentation.view.impl.fragment.BaseMvpFragment;
import com.marcohc.android.clean.architecture.presentation.view.impl.fragment.MenuFragment;
import com.marcohc.android.clean.architecture.presentation.view.inter.MainView;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseMvpActivity<MainView, MainPresenter> implements MenuFragment.MenuFragmentListener, MainView {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    // View
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @InjectView(R.id.leftDrawerContainer)
    ViewGroup leftDrawerContainer;

    // Class
    @SuppressLint("UseSparseArrays")
    private Map<Integer, BaseMvpFragment> fragmentsMap = new HashMap<>();
    private MenuItem menuItem1;
    private MenuItem menuItem2;
    private int positionToGo;
    private ActionBarDrawerToggle mDrawerToggle;
    private MenuFragment menuFragment;
    private BaseMvpFragment currentFragment;
    private Fragment lastFragment;
    private final int INITIAL_POSITION = NavigationManager.SCREENS.NEIGHBOURS_CHAT.ordinal();

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenterImpl();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        ButterKnife.inject(this);

        findFragmentsById();

        initializeComponentBehavior();
    }

    private void findFragmentsById() {
        menuFragment = (MenuFragment) getSupportFragmentManager().findFragmentById(R.id.menuFragment);
        menuFragment.setListener(this);
    }

    public void initializeComponentBehavior() {

        initializeActionBar();

        initializeNavigationDrawer();

        menuClick(INITIAL_POSITION);

        if (StringUtils.isBlank(PreferencesManager.getRegistrationId())) {
            NotificationManager.getInstance().registerInBackground();
        }

        presenter.onViewCreated();
    }

    private void initializeNavigationDrawer() {

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close) {

            public void onDrawerClosed(View view) {

                super.onDrawerClosed(view);

                setMenuItemsVisible(true);

                if (view.getId() == R.id.leftDrawerContainer) {
                    // Wait for the drawer is closed to realize action
                    if (positionToGo != -1) {
                        menuClick(positionToGo);
                        positionToGo = -1;
                    }
                }
                mDrawerToggle.syncState();
            }

            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
                setMenuItemsVisible(false);
                mDrawerToggle.syncState();
            }
        };

        // Set the drawer toggle as the DrawerListener
        drawerLayout.setDrawerListener(mDrawerToggle);

    }

    // ************************************************************************************************************************************************************************
    // * Event handler methods
    // ************************************************************************************************************************************************************************

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (currentFragment != null) {
            currentFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    // MenuMvpFragment events

    @Override
    public void onMenuItemClick(int position) {
        toggleLeftDrawer();
        positionToGo = position;
    }

    // ************************************************************************************************************************************************************************
    // * Auxiliary UI methods
    // ************************************************************************************************************************************************************************

    // Fragment loading methods

    private void menuClick(int position) {

        // Update the main content by replacing fragments
        currentFragment = getFragmentByPosition(position);

        switch (NavigationManager.SCREENS.values()[position]) {
            case INITIATIVES_POSITION:
                getSupportActionBar().setTitle(R.string.initiatives);
                break;
            case NEIGHBOURS_CHAT:
                getSupportActionBar().setTitle(R.string.neighbours_chat);
                break;
            case MY_PROFILE:
                getSupportActionBar().setTitle(R.string.my_profile);
                break;
        }

        NavigationManager.lastViewPosition = NavigationManager.currentViewPosition;
        NavigationManager.currentViewPosition = position;

        Log.v(NavigationManager.LOG_TAG, "Position to go: " + position);
        Log.v(NavigationManager.LOG_TAG, "NavigationManager.lastViewPosition: " + NavigationManager.lastViewPosition);
        Log.v(NavigationManager.LOG_TAG, "MainApplication.currentViewPosition: " + NavigationManager.currentViewPosition);

        if (currentFragment != null) {

            if (lastFragment != null && lastFragment == currentFragment) {
                currentFragment = refreshFragment(position);
            } else {
                loadFragment(currentFragment);
            }

            lastFragment = currentFragment;

            setTitle(currentFragment.getTag());

            // Select menu item
            menuFragment.setSelectedMenuPosition(position);

        }
    }

    private void loadFragment(Fragment currentFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contentFrameLayout, currentFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private BaseMvpFragment refreshFragment(int position) {
        fragmentsMap.remove(position);
        BaseMvpFragment currentFragment = getFragmentByPosition(position);
        loadFragment(currentFragment);
        return currentFragment;
    }

    private BaseMvpFragment getFragmentByPosition(int position) {

        if (fragmentsMap.containsKey(position)) {
            return fragmentsMap.get(position);
        } else {
            switch (NavigationManager.SCREENS.values()[position]) {
                case INITIATIVES_POSITION:
//                    fragmentsMap.put(NavigationManager.SCREENS.INITIATIVES_POSITION.ordinal(), new InitiativesListFragment());
                    break;
                case NEIGHBOURS_CHAT:
//                    fragmentsMap.put(NavigationManager.SCREENS.NEIGHBOURS_CHAT.ordinal(), new NeighboursListFragment());
                    break;
                case MY_PROFILE:
//                    fragmentsMap.put(NavigationManager.SCREENS.MY_PROFILE.ordinal(), new ProfileFragment());
                    break;
            }
            return getFragmentByPosition(position);
        }
    }

    // ************************************************************************************************************************************************************************
    // * Action bar methods
    // ************************************************************************************************************************************************************************

    private void initializeActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_action_logo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        try {
            menuItem1 = menu.findItem(R.id.item_1);
            menuItem1.setVisible(false);
            menuItem1.setEnabled(false);
            menuItem2 = menu.findItem(R.id.item_2);
            menuItem2.setVisible(false);
            menuItem2.setEnabled(false);
        } catch (Exception e) {
//            Crashlytics.log(android.util.Log.ERROR, "MainMvpActivity.onPrepareOptionsMenu", String.format("Exception under control"));
//            Crashlytics.logException(e);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        ButterKnife.inject(this);

        switch (item.getItemId()) {
            case android.R.id.home:
                toggleLeftDrawer();
                break;
            default:
                break;
        }

        return false;
    }

    // ************************************************************************************************************************************************************************
    // * UI auxiliary methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onBackPressed() {

        ButterKnife.inject(this);

        // If the menu is opened, close it
        if (drawerLayout.isDrawerOpen(GravityCompat.START) || drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawers();
            setMenuItemsVisible(true);
        }
        // If the back button comes from AddRecordActivity
        else {
            finish();
        }
    }

    // **********************************************************************************************************************************************************************
    // * UI management methods
    // **********************************************************************************************************************************************************************

    public void toggleLeftDrawer() {
        try {
            if (drawerLayout.isDrawerOpen(leftDrawerContainer)) {
                drawerLayout.closeDrawers();
                setMenuItemsVisible(true);
            } else {
                drawerLayout.openDrawer(leftDrawerContainer);
                setMenuItemsVisible(false);
            }
        } catch (Exception e) {
            android.util.Log.wtf(NavigationManager.LOG_TAG, "Exception in toggleLeftDrawer: " + e.getMessage());
        }
    }

    private void setMenuItemsVisible(boolean flagVisible) {
        if (menuItem1.isEnabled()) {
            menuItem1.setVisible(flagVisible);
        }
        if (menuItem2.isEnabled()) {
            menuItem2.setVisible(flagVisible);
        }
    }

}