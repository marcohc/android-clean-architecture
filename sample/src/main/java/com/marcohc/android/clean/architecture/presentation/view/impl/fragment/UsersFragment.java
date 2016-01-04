package com.marcohc.android.clean.architecture.presentation.view.impl.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.marcohc.android.clean.architecture.domain.model.UserModel;
import com.marcohc.android.clean.architecture.presentation.R;
import com.marcohc.android.clean.architecture.presentation.presenter.impl.UsersPresenterImpl;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.UsersPresenter;
import com.marcohc.android.clean.architecture.presentation.util.NavigationManager;
import com.marcohc.android.clean.architecture.presentation.view.adapter.BaseListAdapter;
import com.marcohc.android.clean.architecture.presentation.view.fragment.BaseMvpFragment;
import com.marcohc.android.clean.architecture.presentation.view.impl.activity.UserDetailActivity;
import com.marcohc.android.clean.architecture.presentation.view.impl.adapter.viewholder.UserViewHolder;
import com.marcohc.android.clean.architecture.presentation.view.inter.UsersView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class UsersFragment extends BaseMvpFragment<UsersView, UsersPresenter> implements UsersView, AdapterView.OnItemClickListener {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    @Bind(R.id.listView)
    ListView listView;

    @Bind(R.id.noDataText)
    TextView noDataText;

    private BaseListAdapter<UserModel> listViewAdapter;
    private UserModel user;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @Override
    protected int getLayoutRes() {
        return R.layout.users_fragment;
    }

    @Override
    public UsersPresenter createPresenter() {
        return new UsersPresenterImpl();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        initializeListView();
        presenter.onViewCreated();
    }

    private void initializeListView() {
        listViewAdapter = new BaseListAdapter<>(getActivity(), R.layout.user_list_item, new ArrayList<UserModel>(), UserViewHolder.class);
        listView.setOnItemClickListener(this);
        listView.setAdapter(listViewAdapter);
    }

    // ************************************************************************************************************************************************************************
    // * Event handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        user = listViewAdapter.getItem(position);
        Intent intent = new Intent(getActivity(), UserDetailActivity.class);
        intent.putExtra(NavigationManager.USER, user.toJsonString());
        startActivity(intent);
    }

    // ************************************************************************************************************************************************************************
    // * View interface methods
    // ************************************************************************************************************************************************************************

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

    // ************************************************************************************************************************************************************************
    // * UI methods
    // ************************************************************************************************************************************************************************

}
