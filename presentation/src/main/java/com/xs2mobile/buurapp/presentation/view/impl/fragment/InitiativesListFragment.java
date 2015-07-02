package com.xs2mobile.buurapp.presentation.view.impl.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.xs2mobile.buurapp.R;
import com.xs2mobile.buurapp.presentation.view.impl.activity.InitiativeCreationActivity;
import com.xs2mobile.buurapp.presentation.view.impl.activity.InitiativeDetailActivity;
import com.xs2mobile.buurapp.presentation.view.impl.adapter.BaseListAdapter;
import com.xs2mobile.buurapp.presentation.util.NavigationManager;
import com.xs2mobile.buurapp.presentation.model.InitiativeModel;
import com.xs2mobile.buurapp.presentation.presenter.impl.InitiativesListPresenterImpl;
import com.xs2mobile.buurapp.presentation.presenter.inter.InitiativesListPresenter;
import com.xs2mobile.buurapp.presentation.util.Constants;
import com.xs2mobile.buurapp.presentation.view.inter.InitiativesListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class InitiativesListFragment extends BaseMvpFragment<InitiativesListView, InitiativesListPresenter> implements InitiativesListView, BaseListAdapter.OnSubViewClickListener, AdapterView.OnItemClickListener {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    @InjectView(R.id.initiativesListView)
    ListView listView;

    @InjectView(R.id.noDataText)
    TextView noDataText;

    private BaseListAdapter listViewAdapter;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @Override
    protected int getLayoutRes() {
        return R.layout.initiatives_fragment;
    }

    @Override
    public InitiativesListPresenter createPresenter() {
        return new InitiativesListPresenterImpl();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        initializeComponentBehavior();
    }

    public void initializeComponentBehavior() {
        initializeListView();
        setHasOptionsMenu(true);
        presenter.onViewCreated();
    }

    private void initializeListView() {
        listViewAdapter = new BaseListAdapter(this, R.layout.initiative_list_item, new ArrayList<InitiativeModel>(), Constants.DATA_TYPE.INITIATIVE);
        listView.setOnItemClickListener(this);
        listView.setAdapter(listViewAdapter);
    }

    // ************************************************************************************************************************************************************************
    // * Event handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.item_2);
        menuItem.setEnabled(true);
        menuItem.setVisible(true);
        menuItem.setTitle(R.string.create_initiative);
        menuItem.setIcon(R.drawable.ic_action_initiative);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_2:
                presenter.onInitiativeActionClick();
                break;
            default:
                break;
        }

        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            presenter.onViewCreated();
        }
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void onSubViewItemClick(View view, int position, Object data) {
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onItemClick(position);
    }

    // ************************************************************************************************************************************************************************
    // * Presenter methods
    // ************************************************************************************************************************************************************************

    @Override
    public void goToInitiativeCreation() {
        Intent intent = new Intent(getActivity(), InitiativeCreationActivity.class);
        getActivity().startActivityForResult(intent, 1);
    }

    @Override
    public void goToInitiativeDetail(int position) {
        Intent intent = new Intent(getActivity(), InitiativeDetailActivity.class);
        intent.putExtra(NavigationManager.INITIATIVE, listViewAdapter.getItem(position).toJsonString());
        getActivity().startActivityForResult(intent, 1);
    }

    @Override
    public void loadListData(List<InitiativeModel> itemsList) {
        setDataToListView(itemsList);
    }

    // ************************************************************************************************************************************************************************
    // * UI methods
    // ************************************************************************************************************************************************************************

    private void setDataToListView(List<InitiativeModel> messageModelList) {
        if (messageModelList.isEmpty()) {
            noDataText.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        } else {
            noDataText.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            listViewAdapter.clear();
            listViewAdapter.addThemAll(messageModelList);
            listViewAdapter.notifyDataSetChanged();
        }
    }
}
