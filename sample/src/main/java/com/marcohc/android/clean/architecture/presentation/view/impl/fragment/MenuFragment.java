package com.marcohc.android.clean.architecture.presentation.view.impl.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.marcohc.android.clean.architecture.domain.model.MenuItemModel;
import com.marcohc.android.clean.architecture.presentation.presenter.impl.MenuPresenterImpl;
import com.marcohc.android.clean.architecture.presentation.presenter.inter.MenuPresenter;
import com.marcohc.android.clean.architecture.presentation.util.AppConfigHelper;
import com.marcohc.android.clean.architecture.presentation.view.adapter.BaseListAdapter;
import com.marcohc.android.clean.architecture.presentation.view.fragment.BaseMvpFragment;
import com.marcohc.android.clean.architecture.presentation.view.impl.activity.AuthenticationActivity;
import com.marcohc.android.clean.architecture.presentation.view.impl.adapter.viewholder.MenuViewHolder;
import com.marcohc.android.clean.architecture.presentation.view.inter.MenuView;
import com.marcohc.android.clean.architecture.sample.BuildConfig;
import com.marcohc.android.clean.architecture.sample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class MenuFragment extends BaseMvpFragment<MenuView, MenuPresenter> implements MenuView {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    // View

    @Bind(R.id.isDevelopmentText)
    TextView isDevelopmentText;

    @Bind(R.id.menuListView)
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

        menuListView.setAdapter(new BaseListAdapter<>(getActivity(), R.layout.menu_list_item, itemsList, MenuViewHolder.class));
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

    @OnClick(R.id.logOutContainer)
    protected void onLogOutContainerClick() {
        new MaterialDialog.Builder(getActivity())
                .title(R.string.log_out_question)
                .positiveText(R.string.yes)
                .negativeText(R.string.no)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        presenter.onLogOutContainerClick();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    // ************************************************************************************************************************************************************************
    // * View interface methods
    // ************************************************************************************************************************************************************************

    @Override
    public void gotToAuthentication() {
        Intent intent = new Intent(getActivity(), AuthenticationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        getActivity().finish();
    }

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
        if (AppConfigHelper.isDevelopment() || AppConfigHelper.isAcceptance()) {
            isDevelopmentText.setText(String.format("%s / %s / %s", AppConfigHelper.isDevelopment() ? "Development" : "Acceptance", BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE));
            isDevelopmentText.setVisibility(View.VISIBLE);
        } else {
            isDevelopmentText.setVisibility(View.GONE);
        }
    }
}
