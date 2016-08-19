package com.marcohc.architecture.app.presentation.fragment.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.marcohc.architecture.app.R;
import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.app.presentation.activity.impl.UserDetailActivity;
import com.marcohc.architecture.app.presentation.adapter.viewholder.UserViewHolder;
import com.marcohc.architecture.app.presentation.fragment.inter.UsersListView;
import com.marcohc.architecture.app.presentation.presenter.impl.UsersListPresenterImpl;
import com.marcohc.architecture.app.presentation.presenter.inter.UsersListPresenter;
import com.marcohc.architecture.app.presentation.util.NavigationManager;
import com.marcohc.architecture.presentation.view.adapter.BaseListAdapter;
import com.marcohc.architecture.presentation.view.fragment.BaseMvpFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;

public class UsersListFragment extends BaseMvpFragment<UsersListView, UsersListPresenter> implements UsersListView, SwipeRefreshLayout.OnRefreshListener, BaseListAdapter.ChildViewClickListener {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    // View
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.listView)
    ListView listView;

    // Class
    private BaseListAdapter<UserModel> listViewAdapter;
    private EmptyStateFragment emptyStateFragment;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @Override
    protected int getLayoutRes() {
        return R.layout.users_list_fragment;
    }

    @NonNull
    @Override
    public UsersListPresenter createPresenter() {
        return new UsersListPresenterImpl();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        setUpView();
    }

    private void setUpView() {
        setUpListView();
        setUpEmptyStateFragment();
        setUpSwipeRefreshLayout();
        presenter.onViewCreated();
    }

    private void setUpListView() {
        listViewAdapter = new BaseListAdapter<>(getActivity(), R.layout.user_list_item, UserViewHolder.class);
        listViewAdapter.setOnChildViewClick(this);
        listView.setAdapter(listViewAdapter);
    }

    private void setUpEmptyStateFragment() {
        emptyStateFragment = (EmptyStateFragment) getChildFragmentManager().findFragmentById(R.id.emptyStateFragment);
        emptyStateFragment.setTitle(getString(R.string.no_users_title));
        emptyStateFragment.setSubTitle(getString(R.string.no_users_subtitle));
        emptyStateFragment.setImage(R.drawable.im_user_place_holder);
    }

    private void setUpSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getActivity(), R.color.primary), ContextCompat.getColor(getActivity(), R.color.primary_dark));
    }

    // ************************************************************************************************************************************************************************
    // * Event handler methods
    // ************************************************************************************************************************************************************************

    @OnItemClick(R.id.listView)
    protected void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        UserModel user = listViewAdapter.getItem(position);
        Intent intent = new Intent(getActivity(), UserDetailActivity.class);
        intent.putExtra(NavigationManager.USER, user.toJsonString());
        startActivity(intent);
    }

    @Override
    public void onChildViewClick(View view, int position) {
        UserModel model = listViewAdapter.getItem(position);
        switch (view.getId()) {
            case R.id.userImageView:
                showMessage(String.format("The user image of %s at position %d has been click", model != null ? model.getName() : "null", position));
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh() {
        presenter.onRefresh();
    }

    // ************************************************************************************************************************************************************************
    // * View interface methods
    // ************************************************************************************************************************************************************************

    @Override
    public void loadData(List<UserModel> modelList) {
        swipeRefreshLayout.setRefreshing(false);
        if (modelList.isEmpty()) {
            emptyStateFragment.setVisible(true);
            listView.setVisibility(View.GONE);
        } else {
            emptyStateFragment.setVisible(false);
            listView.setVisibility(View.VISIBLE);
            listViewAdapter.clear();
            listViewAdapter.addThemAll(modelList);
            listViewAdapter.notifyDataSetChanged();
        }
    }

}
