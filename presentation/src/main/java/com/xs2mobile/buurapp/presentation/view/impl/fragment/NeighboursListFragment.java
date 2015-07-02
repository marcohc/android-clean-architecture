package com.xs2mobile.buurapp.presentation.view.impl.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.xs2mobile.buurapp.R;
import com.xs2mobile.buurapp.presentation.model.ChatModel;
import com.xs2mobile.buurapp.presentation.model.NeighbourChatModel;
import com.xs2mobile.buurapp.presentation.model.UserModel;
import com.xs2mobile.buurapp.presentation.presenter.impl.NeighboursListPresenterImpl;
import com.xs2mobile.buurapp.presentation.presenter.inter.NeighboursListPresenter;
import com.xs2mobile.buurapp.presentation.util.Constants;
import com.xs2mobile.buurapp.presentation.util.NavigationManager;
import com.xs2mobile.buurapp.presentation.view.impl.activity.NeighboursChatActivity;
import com.xs2mobile.buurapp.presentation.view.impl.adapter.BaseListAdapter;
import com.xs2mobile.buurapp.presentation.view.inter.NeighboursListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class NeighboursListFragment extends BaseMvpFragment<NeighboursListView, NeighboursListPresenter> implements NeighboursListView, BaseListAdapter.OnSubViewClickListener, AdapterView.OnItemClickListener {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    @InjectView(R.id.listView)
    ListView listView;

    @InjectView(R.id.noDataText)
    TextView noDataText;

    private BaseListAdapter listViewAdapter;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @Override
    protected int getLayoutRes() {
        return R.layout.neighbours_list_fragment;
    }

    @Override
    public NeighboursListPresenter createPresenter() {
        return new NeighboursListPresenterImpl();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        initializeComponentBehavior();
    }

    public void initializeComponentBehavior() {
        initializeListView();
        presenter.onViewCreated();
    }

    private void initializeListView() {
        listViewAdapter = new BaseListAdapter(this, R.layout.neighbours_list_item, new ArrayList<NeighbourChatModel>(), Constants.DATA_TYPE.NEIGHBOURS);
        listView.setOnItemClickListener(this);
        listView.setAdapter(listViewAdapter);
    }

    // ************************************************************************************************************************************************************************
    // * Event handler methods
    // ************************************************************************************************************************************************************************

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
        presenter.onItemClick((NeighbourChatModel) listViewAdapter.getItem(position));
    }

    @Override
    public void loadData(List<NeighbourChatModel> modelList) {
        setDataToListView(modelList);
    }

    @Override
    public void goToNeighboursChat(UserModel user, ChatModel chat) {
        Intent intent = new Intent(getActivity(), NeighboursChatActivity.class);
        intent.putExtra(NavigationManager.USER, user.toJsonString());
        intent.putExtra(NavigationManager.CHAT, chat.toJsonString());
        getActivity().startActivityForResult(intent, 1);
    }

    // ************************************************************************************************************************************************************************
    // * View interface methods
    // ************************************************************************************************************************************************************************

    // ************************************************************************************************************************************************************************
    // * UI methods
    // ************************************************************************************************************************************************************************

    private void setDataToListView(List<NeighbourChatModel> modelList) {
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
}
