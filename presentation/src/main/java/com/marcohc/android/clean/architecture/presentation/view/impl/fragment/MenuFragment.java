package com.marcohc.android.clean.architecture.presentation.view.impl.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.MaterialDialog.ButtonCallback;
import com.marcohc.android.clean.architecture.MainApplication;
import com.marcohc.android.clean.architecture.common.model.MenuItemModel;
import com.marcohc.android.clean.architecture.presentation.R;
import com.marcohc.android.clean.architecture.presentation.presenter.impl.MenuPresenterImpl;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.MenuPresenter;
import com.marcohc.android.clean.architecture.presentation.view.impl.activity.MainActivity;
import com.marcohc.android.clean.architecture.presentation.view.impl.activity.StartActivity;
import com.marcohc.android.clean.architecture.presentation.view.impl.adapter.BaseListAdapter;
import com.marcohc.android.clean.architecture.presentation.view.impl.adapter.viewholder.MenuViewHolder;
import com.marcohc.android.clean.architecture.presentation.view.inter.MenuView;
import com.marcohc.helperoid.DialogHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

public class MenuFragment extends BaseMvpFragment<MenuView, MenuPresenter> implements BaseListAdapter.OnSubViewClickListener, MenuView, AdapterView.OnItemClickListener {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    // Header
    @InjectView(R.id.isDevelopmentText)
    TextView isDevelopmentText;

    // Content
    @InjectView(R.id.menuListView)
    ListView menuListView;

    private MenuFragmentListener listener;
    private BaseListAdapter menuListViewAdapter;

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
        initializeComponentBehavior();
    }

    public void initializeComponentBehavior() {

        if (MainApplication.isDevelopment()) {
            isDevelopmentText.setVisibility(View.VISIBLE);
        }

        initializeMenuListView();
    }

    private void initializeMenuListView() {
        List<MenuItemModel> itemsList = new ArrayList<>();
        String[] textsStringArray = getResources().getStringArray(R.array.menu_text_array);
        TypedArray normalIconsTypedArray = getResources().obtainTypedArray(R.array.menu_normal_icons_array);
        for (int i = 0; i < textsStringArray.length; i++) {
            itemsList.add(new MenuItemModel(textsStringArray[i], normalIconsTypedArray.getResourceId(i, -1)));
        }
        normalIconsTypedArray.recycle();

        menuListViewAdapter = new BaseListAdapter(this, R.layout.menu_list_item, itemsList, MenuViewHolder.class);
        menuListView.setOnItemClickListener(this);
        menuListView.setAdapter(menuListViewAdapter);
    }

    // ************************************************************************************************************************************************************************
    // * Event handler methods
    // ************************************************************************************************************************************************************************

    @OnClick(R.id.logOutContainer)
    protected void onLogOutContainerClick() {

        DialogHelper.showConfirmationDialog(R.string.log_out_question, R.string.yes, R.string.no, getActivity(), new ButtonCallback() {
            @Override
            public void onPositive(MaterialDialog dialog) {
                super.onPositive(dialog);
                presenter.onLogOutContainerClick();
            }

            @Override
            public void onNegative(MaterialDialog dialog) {
                super.onNegative(dialog);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onSubViewItemClick(View view, int position, Object data) {
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        view.setSelected(true);
        menuListView.setItemChecked(position, true);
        presenter.onMenuItemClick(position);
    }

    // ************************************************************************************************************************************************************************
    // * View interface methods
    // ************************************************************************************************************************************************************************

    @Override
    public void goToStart() {
        Intent intent = new Intent(getActivity(), StartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void dispatchMenuItemClick(int position) {
        listener.onMenuItemClick(position);
    }

    // ************************************************************************************************************************************************************************
    // * UI methods
    // ************************************************************************************************************************************************************************

    @Override
    public Context getContext() {
        return getActivity();
    }

    public void setListener(MainActivity listener) {
        this.listener = listener;
    }

    public void setSelectedMenuPosition(int position) {
        menuListView.setItemChecked(position, true);
    }

    public interface MenuFragmentListener {
        void onMenuItemClick(int position);
    }
}
