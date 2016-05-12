package com.marcohc.architecture.app.presentation.view.impl.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.marcohc.architecture.app.R;
import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.app.presentation.presenter.impl.UsersPresenterImpl;
import com.marcohc.architecture.app.presentation.presenter.inter.UsersPresenter;
import com.marcohc.architecture.app.presentation.util.NavigationManager;
import com.marcohc.architecture.app.presentation.view.impl.activity.UserDetailActivity;
import com.marcohc.architecture.app.presentation.view.impl.adapter.viewholder.UserRecyclerViewHolder;
import com.marcohc.architecture.app.presentation.view.inter.UsersView;
import com.marcohc.architecture.presentation.view.adapter.BaseRecyclerAdapter;
import com.marcohc.architecture.presentation.view.fragment.BaseMvpFragment;
import com.marcohc.toasteroid.Toasteroid;

import java.util.List;

import butterknife.Bind;

public class UsersRecycleFragment extends BaseMvpFragment<UsersView, UsersPresenter> implements UsersView, SwipeRefreshLayout.OnRefreshListener, BaseRecyclerAdapter.ItemViewClickListener {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private BaseRecyclerAdapter<UserModel, UserRecyclerViewHolder> recyclerViewAdapter;
    private EmptyStateFragment emptyStateFragment;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @Override
    protected int getLayoutRes() {
        return R.layout.users_recycle_fragment;
    }

    @NonNull
    @Override
    public UsersPresenter createPresenter() {
        return new UsersPresenterImpl();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        setUpListView();
        setUpEmptyStateFragment();
        setUpSwipeRefreshLayout();
        presenter.onViewCreated();
    }

    private void setUpEmptyStateFragment() {
        emptyStateFragment = (EmptyStateFragment) getChildFragmentManager().findFragmentById(R.id.emptyStateFragment);
        emptyStateFragment.setTitle(getString(R.string.no_users_title));
        emptyStateFragment.setSubTitle(getString(R.string.no_users_subtitle));
        emptyStateFragment.setImage(R.drawable.im_user_place_holder);
    }

    private void setUpListView() {
        recyclerViewAdapter = new BaseRecyclerAdapter<>(getActivity(), R.layout.user_list_item, UserRecyclerViewHolder.class);
        recyclerViewAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void setUpSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getActivity(), R.color.primary), ContextCompat.getColor(getActivity(), R.color.primary_dark));
    }

    // ************************************************************************************************************************************************************************
    // * Event handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onItemViewClick(View view, int position) {
        UserModel user = recyclerViewAdapter.getItem(position);
        switch (view.getId()) {
            case R.id.userImage:
                Toasteroid.dismiss();
                showInfo(String.format("Position: %d, User: %s)", position, user.toString()));
                break;
            default:
                Intent intent = new Intent(getActivity(), UserDetailActivity.class);
                intent.putExtra(NavigationManager.USER, user.toJsonString());
                startActivity(intent);
                break;
        }
    }

    // ************************************************************************************************************************************************************************
    // * View interface methods
    // ************************************************************************************************************************************************************************

    @Override
    public void loadData(List<UserModel> modelList) {
        swipeRefreshLayout.setRefreshing(false);
        if (modelList.isEmpty()) {
            emptyStateFragment.setVisible(true);
            recyclerView.setVisibility(View.GONE);
        } else {
            emptyStateFragment.setVisible(false);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerViewAdapter.addAll(modelList);
        }
    }

    @Override
    public void onRefresh() {
        presenter.onRefresh();
    }
}
