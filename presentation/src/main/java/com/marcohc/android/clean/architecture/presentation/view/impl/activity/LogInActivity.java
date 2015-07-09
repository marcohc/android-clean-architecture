package com.marcohc.android.clean.architecture.presentation.view.impl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.marcohc.android.clean.architecture.domain.model.BaseModel;
import com.marcohc.android.clean.architecture.domain.model.UserModel;
import com.marcohc.android.clean.architecture.presentation.R;
import com.marcohc.android.clean.architecture.presentation.presenter.impl.LogInPresenterImpl;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.LogInPresenter;
import com.marcohc.android.clean.architecture.presentation.view.impl.adapter.BaseListAdapter;
import com.marcohc.android.clean.architecture.presentation.view.impl.adapter.viewholder.UserViewHolder;
import com.marcohc.android.clean.architecture.presentation.view.inter.LogInView;
import com.marcohc.helperoid.ScreenHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class LogInActivity extends BaseMvpActivity<LogInView, LogInPresenter> implements LogInView, BaseListAdapter.OnSubViewClickListener, AdapterView.OnItemClickListener {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    // View
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.usernameEditText)
    EditText usernameEditText;

    @InjectView(R.id.passwordEditText)
    EditText passwordEditText;

    @InjectView(R.id.listView)
    ListView listView;

    @InjectView(R.id.noDataText)
    TextView noDataText;

    private BaseListAdapter listViewAdapter;
    private UserModel user;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @Override
    public LogInPresenter createPresenter() {
        return new LogInPresenterImpl();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_activity);

        initializeActionBar();

        initializeListView();

        presenter.onViewCreated();
    }

    private void initializeActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    private void initializeListView() {
        listViewAdapter = new BaseListAdapter(this, R.layout.user_list_item, new ArrayList<UserModel>(), UserViewHolder.class);
        listView.setOnItemClickListener(this);
        listView.setAdapter(listViewAdapter);
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
        menuItem2.setIcon(R.drawable.abc_ic_go_search_api_mtrl_alpha);
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
                presenter.onActionDoneClick();
                break;
            default:
                break;
        }

        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        user = (UserModel) listViewAdapter.getItem(position);
        usernameEditText.setText(user.getUsername());
        passwordEditText.setText(user.getPassword());
    }

    @Override
    public void onSubViewItemClick(View view, int position, BaseModel data) {
    }

    @Override
    public Context getContext() {
        return this;
    }

    // ************************************************************************************************************************************************************************
    // * View interface methods
    // ************************************************************************************************************************************************************************

    @Override
    public String getUsername() {
        return usernameEditText.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordEditText.getText().toString();
    }

    @Override
    public void invalidateUsername() {
        YoYo.with(Techniques.Shake).playOn(usernameEditText);
    }

    @Override
    public void invalidatePassword() {
        YoYo.with(Techniques.Shake).playOn(passwordEditText);
    }

    @Override
    public void loadData(List<UserModel> modelList) {
        if (modelList.isEmpty()) {
            noDataText.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        } else {
            noDataText.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            listViewAdapter.clear();
            listViewAdapter.addThemAll(modelList);
            listViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public UserModel getUser() {
        return user;
    }

    // ************************************************************************************************************************************************************************
    // * UI methods
    // ************************************************************************************************************************************************************************

    private void hideKeyBoard() {
        ScreenHelper.hideKeyboard(this);
    }

}
