package com.marcohc.architecture.app.presentation.fragment.impl;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.marcohc.architecture.app.BuildConfig;
import com.marcohc.architecture.app.R;
import com.marcohc.architecture.app.domain.model.MenuItemModel;
import com.marcohc.architecture.app.presentation.adapter.viewholder.MenuViewHolder;
import com.marcohc.architecture.app.presentation.fragment.inter.MenuView;
import com.marcohc.architecture.app.presentation.presenter.impl.MenuPresenterImpl;
import com.marcohc.architecture.app.presentation.presenter.inter.MenuPresenter;
import com.marcohc.architecture.app.presentation.util.BuildConfigHelper;
import com.marcohc.architecture.presentation.view.adapter.BaseListAdapter;
import com.marcohc.architecture.presentation.view.fragment.BaseMvpFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;

public class MenuFragment extends BaseMvpFragment<MenuView, MenuPresenter> implements MenuView {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    // View

    @BindView(R.id.isDevelopmentText)
    TextView isDevelopmentText;

    @BindView(R.id.menuListView)
    ListView menuListView;

    // Class

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @Override
    protected int getLayoutRes() {
        return R.layout.menu_fragment;
    }

    @Override
    public MenuPresenter createPresenter() {
        return new MenuPresenterImpl();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        setUpView();
    }

    public void setUpView() {
        setDevelopmentText();
        setUpMenuListView();
    }

    private void setUpMenuListView() {
        List<MenuItemModel> itemsList = new ArrayList<>();
        String[] textsStringArray = getResources().getStringArray(R.array.menu_text_array);
        TypedArray normalIconsTypedArray = getResources().obtainTypedArray(R.array.menu_normal_icons_array);
        for (int i = 0; i < textsStringArray.length; i++) {
            itemsList.add(new MenuItemModel(textsStringArray[i], normalIconsTypedArray.getResourceId(i, -1)));
        }
        normalIconsTypedArray.recycle();

        menuListView.setAdapter(new BaseListAdapter<>(getActivity(), R.layout.menu_list_item, MenuViewHolder.class, itemsList));
    }

    // ************************************************************************************************************************************************************************
    // * Event handler methods
    // ************************************************************************************************************************************************************************

    @OnItemClick(R.id.menuListView)
    protected void onMenuItemClick(AdapterView<?> parent, View view, int position, long id) {
        view.setSelected(true);
        menuListView.setItemChecked(position, true);
        presenter.onMenuItemClick(position);
    }

    // ************************************************************************************************************************************************************************
    // * View interface methods
    // ************************************************************************************************************************************************************************

    @Override
    public void setSelectedMenuItem(int position) {
        menuListView.setItemChecked(position, true);
    }

    // ************************************************************************************************************************************************************************
    // * UI methods
    // ************************************************************************************************************************************************************************

    @Override
    public Context getContext() {
        return getActivity();
    }

    private void setDevelopmentText() {
        if (!BuildConfigHelper.getInstance().isProduction()) {
            isDevelopmentText.setText(String.format("%s / %s / %s", BuildConfigHelper.getInstance().getBuildVariantName(), BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE));
            isDevelopmentText.setVisibility(View.VISIBLE);
        } else {
            isDevelopmentText.setVisibility(View.GONE);
        }
    }
}
