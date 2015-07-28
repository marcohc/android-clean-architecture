package com.marcohc.android.clean.architecture.presentation.view.impl.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.marcohc.android.clean.architecture.domain.model.BaseModel;
import com.marcohc.android.clean.architecture.domain.model.UserModel;
import com.marcohc.android.clean.architecture.presentation.R;
import com.marcohc.android.clean.architecture.presentation.presenter.impl.ProfilePresenterImpl;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.ProfilePresenter;
import com.marcohc.android.clean.architecture.presentation.view.impl.adapter.BaseListAdapter;
import com.marcohc.android.clean.architecture.presentation.view.impl.adapter.viewholder.UserViewHolder;
import com.marcohc.android.clean.architecture.presentation.view.inter.ProfileView;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class ProfileFragment extends BaseMvpFragment<ProfileView, ProfilePresenter> implements ProfileView, BaseListAdapter.OnSubViewClickListener, AdapterView.OnItemClickListener {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************
    @Bind(R.id.userImage)
    ImageView userImage;

    @Bind(R.id.usernameText)
    TextView usernameText;

    @Bind(R.id.addressText)
    TextView addressText;

    @Bind(R.id.dateOfBirthAndEmailText)
    TextView dateOfBirthAndEmailText;

    @Bind(R.id.listView)
    ListView listView;

    @Bind(R.id.noDataText)
    TextView noDataText;

    private BaseListAdapter listViewAdapter;
    private UserModel user;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @Override
    protected int getLayoutRes() {
        return R.layout.profile_fragment;
    }

    @Override
    public ProfilePresenter createPresenter() {
        return new ProfilePresenterImpl();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        initializeListView();
        presenter.onViewCreated();
    }

    private void initializeListView() {
        listViewAdapter = new BaseListAdapter(this, R.layout.user_list_item, new ArrayList<UserModel>(), UserViewHolder.class);
        listView.setOnItemClickListener(this);
        listView.setAdapter(listViewAdapter);
    }

    // ************************************************************************************************************************************************************************
    // * Event handler methods
    // ************************************************************************************************************************************************************************

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        user = (UserModel) listViewAdapter.getItem(position);
        setUserData(user);
    }

    @Override
    public void onSubViewItemClick(View view, int position, BaseModel data) {
    }

    @Override
    public Context getContext() {
        return getActivity();
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

    private void setUserData(UserModel user) {
        if (!StringUtils.isBlank(user.getPicture().getThumbnail())) {
            Picasso.with(getActivity()).load(user.getPicture().getThumbnail()).into(userImage);
        }
        usernameText.setText(user.getUsername());
        addressText.setText(user.getLocation().getStreet());
        dateOfBirthAndEmailText.setText(user.getEmail());
    }
}
