package com.marcohc.architecture.app.presentation.story.user.userlist.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.marcohc.architecture.aca.presentation.adapter.BaseListAdapter;
import com.marcohc.architecture.aca.presentation.mvp.BaseMvpActivity;
import com.marcohc.architecture.app.R;
import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.app.presentation.navigation.Navigator;
import com.marcohc.architecture.app.presentation.story.user.userlist.viewholder.UserViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Activity which displays random users in a list with different display options.
 *
 * @author Marco Hernaiz
 * @since 08/08/16
 */
public class UsersListActivity extends BaseMvpActivity<UsersListView, UsersListPresenter> implements UsersListView, SwipeRefreshLayout.OnRefreshListener, BaseListAdapter.ChildViewClickListener {

    //region Attributes
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.usersDataSourceSwitch)
    SwitchCompat mUsersDataSourceSwitch;
    @BindView(R.id.cacheSwitch)
    SwitchCompat mCacheSwitch;
    @BindView(R.id.listView)
    ListView mListView;
    @BindView(R.id.timeSpentTextView)
    TextView mTimeSpentTextView;
    @BindView(R.id.cancelButton)
    Button mCancelButton;

    private BaseListAdapter<UserModel> mListViewAdapter;
    private ProgressDialog dialog;
    //endregion

    //region Set up methods
    @NonNull
    @Override
    public UsersListPresenter createPresenter() {
        return new UsersListPresenterImpl();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_users_list;
    }

    @Override
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
        setUpView();
    }

    private void setUpView() {
        setUpActionBar();
        setUpEventListeners();
        setUpListView();
        setUpSwipeRefreshLayout();
        presenter.onViewCreated();
    }

    private void setUpActionBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.user_detail);
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setHomeButtonEnabled(false);
        }
    }

    private void setUpEventListeners() {
        mUsersDataSourceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                presenter.onUsersDataSourceSwitchChecked(isChecked);
            }
        });
        mCacheSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                presenter.onCacheSwitchChecked(isChecked);
            }
        });
    }

    private void setUpListView() {
        mListViewAdapter = new BaseListAdapter<>(this, R.layout.list_item_user, UserViewHolder.class);
        mListViewAdapter.setOnChildViewClick(this);
        mListView.setAdapter(mListViewAdapter);
    }

    private void setUpSwipeRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.primary), ContextCompat.getColor(this, R.color.primary_dark));
    }
    //endregion

    //region Event handler methods
    @OnItemClick(R.id.listView)
    protected void onItemClick(int position) {
        presenter.onItemClick(mListViewAdapter.getItem(position));
    }

    @OnClick(R.id.requestDataButton)
    protected void onRequestDataButtonClick() {
        presenter.onRequestDataButtonClick();
    }

    @OnClick(R.id.cancelButton)
    protected void onCancelButtonClick() {
        presenter.onCancelButtonClick();
    }

    @Override
    public void onChildViewClick(View view, int position) {
        if (view.getId() == R.id.userImageView) {
            UserModel user = mListViewAdapter.getItem(position);
            presenter.onChildViewClick(user, position);
        }
    }

    @Override
    public void onRefresh() {
        presenter.onRefresh();
    }
    //endregion

    //region View interface methods

    @Override
    public void enableCancelButton(boolean enable) {
        mCancelButton.setEnabled(enable);
    }

    @Override
    public void goToUserDetail(@NonNull UserModel model) {
        Navigator.goToUserDetail(this, model);
    }

    @Override
    public void renderModelList(@Nullable List<UserModel> modelList) {
        mSwipeRefreshLayout.setRefreshing(false);
        if (modelList == null || modelList.isEmpty()) {
            mListView.setVisibility(View.GONE);
        } else {
            mListView.setVisibility(View.VISIBLE);
            mListViewAdapter.clear();
            mListViewAdapter.addThemAll(modelList);
            mListViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setTimeSpent(@NonNull Long timeSpentInMilliseconds) {
        mTimeSpentTextView.setText(String.format("%d %s", timeSpentInMilliseconds, "ms"));
    }

    @Override
    public void showLoadingDialog() {
        hideDialog();
        dialog = ProgressDialog.show(this, null, getString(R.string.loading), true);
    }

    @Override
    public void showRefreshSpinner(boolean show) {
        mSwipeRefreshLayout.setRefreshing(show);
    }

    @UiThread
    @Override
    public void hideDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @UiThread
    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    //endregion

}
