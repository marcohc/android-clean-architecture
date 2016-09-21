package com.marcohc.architecture.app.presentation.story.main;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.marcohc.architecture.app.R;
import com.marcohc.architecture.app.presentation.story.user.list.UsersListFragment;
import com.marcohc.architecture.app.presentation.story.user.list.UsersRecycleFragment;
import com.marcohc.architecture.app.presentation.util.NavigationManager;
import com.marcohc.architecture.presentation.view.activity.BaseMvpActivity;
import com.marcohc.architecture.presentation.view.fragment.BaseMvpFragment;

import butterknife.BindView;
import timber.log.Timber;

public class MainActivity extends BaseMvpActivity<MainView, MainPresenter> implements MainView {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    // View
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.leftDrawerContainer)
    ViewGroup leftDrawerContainer;

    // Class
    private MenuItem menuItem1;
    private MenuItem menuItem2;
    private int positionToGo;
    private ActionBarDrawerToggle mDrawerToggle;
    private BaseMvpFragment currentFragment;
    private final int INITIAL_POSITION = NavigationManager.SCREENS.USERS_LIST.ordinal();
    private ActionBar actionBar;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenterImpl();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity;
    }

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        setUpView();
    }

    private void setUpView() {
        setUpActionBar();
        setUpNavigationDrawer();
        menuClick(INITIAL_POSITION);
        presenter.onViewCreated();
    }

    private void setUpActionBar() {
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    private void setUpNavigationDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                setMenuItemsVisible(true);
                // Wait for the drawer is closed to realize action
                if (view.getId() == R.id.leftDrawerContainer) {
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
        drawerLayout.setDrawerListener(mDrawerToggle);
    }

    // ************************************************************************************************************************************************************************
    // * Event handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menuItem1 = menu.findItem(R.id.item_1);
        menuItem1.setVisible(false);
        menuItem1.setEnabled(false);
        menuItem2 = menu.findItem(R.id.item_2);
        menuItem2.setVisible(false);
        menuItem2.setEnabled(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                toggleLeftDrawer();
                break;
            default:
                break;
        }

        return false;
    }

    @Override
    public void onBackPressed() {
        // If the menu is opened, close it
        if (drawerLayout.isDrawerOpen(GravityCompat.START) || drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawers();
            setMenuItemsVisible(true);
        } else {
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Timber.v("{requestCode: %d, resultCode: %d, data: %s}", requestCode, resultCode, data != null ? data.toString() : "");
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

    // ************************************************************************************************************************************************************************
    // * View handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onMenuItemClick(int position) {
        positionToGo = position;
        toggleLeftDrawer();
    }

    // ************************************************************************************************************************************************************************
    // * Auxiliary UI methods
    // ************************************************************************************************************************************************************************

    // Fragment loading methods

    private void menuClick(int position) {

        // Update the main content by replacing fragments
        currentFragment = getFragment(position);

        setTitleByPosition(NavigationManager.SCREENS.values()[position]);

        if (currentFragment != null) {
            loadFragment(currentFragment);
            setTitle(currentFragment.getTag());
            // Select menu item
            presenter.setSelectedMenuPosition(position);
        }
    }

    private void setTitleByPosition(NavigationManager.SCREENS screens) {
        switch (screens) {
            case USERS_LIST:
                actionBar.setTitle(getString(R.string.users_list));
                break;
            case USERS_RECYCLE:
                actionBar.setTitle(getString(R.string.users_recycle));
                break;
        }
    }

    private void loadFragment(Fragment currentFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contentFrameLayout, currentFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private BaseMvpFragment getFragment(int position) {
        switch (NavigationManager.SCREENS.values()[position]) {
            case USERS_LIST:
                return new UsersListFragment();
            case USERS_RECYCLE:
                return new UsersRecycleFragment();
            default:
                return null;
        }
    }

    private void toggleLeftDrawer() {
        try {
            if (drawerLayout.isDrawerOpen(leftDrawerContainer)) {
                drawerLayout.closeDrawers();
                setMenuItemsVisible(true);
            } else {
                drawerLayout.openDrawer(leftDrawerContainer);
                setMenuItemsVisible(false);
            }
        } catch (Exception e) {
            Timber.wtf("Exception in toggleLeftDrawer: %s", e.getMessage());
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