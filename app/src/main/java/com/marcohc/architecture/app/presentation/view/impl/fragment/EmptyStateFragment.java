package com.marcohc.architecture.app.presentation.view.impl.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marcohc.architecture.app.R;
import com.marcohc.architecture.app.presentation.presenter.impl.EmptyStatePresenterImpl;
import com.marcohc.architecture.app.presentation.presenter.inter.EmptyStatePresenter;
import com.marcohc.architecture.app.presentation.view.BaseView;
import com.marcohc.architecture.app.presentation.view.fragment.BaseMvpFragment;
import com.marcohc.architecture.app.presentation.view.inter.EmptyStateView;

import butterknife.Bind;

public class EmptyStateFragment extends BaseMvpFragment<EmptyStateView, EmptyStatePresenter> implements BaseView {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************
    @Bind(R.id.noDataContainer)
    ViewGroup noDataContainer;

    @Bind(R.id.noDataImage)
    ImageView noDataImage;

    @Bind(R.id.noDataTitleText)
    TextView noDataTitleText;

    @Bind(R.id.noDataSubtitleText)
    TextView noDataSubtitleText;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @Override
    public int getLayoutRes() {
        return R.layout.empty_state_fragment;
    }

    @Override
    public EmptyStatePresenter createPresenter() {
        return new EmptyStatePresenterImpl();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
    }

    // ************************************************************************************************************************************************************************
    // * UI methods
    // ************************************************************************************************************************************************************************

    public void setVisible(boolean visible) {
        if (visible) {
            noDataContainer.setVisibility(View.VISIBLE);
        } else {
            noDataContainer.setVisibility(View.GONE);
        }
    }

    public void setTitle(String title) {
        noDataTitleText.setText(title);
    }

    public void setSubTitle(String subTitle) {
        noDataSubtitleText.setText(subTitle);
    }

    public void setImage(int drawableId) {
        noDataImage.setImageResource(drawableId);
    }
}
