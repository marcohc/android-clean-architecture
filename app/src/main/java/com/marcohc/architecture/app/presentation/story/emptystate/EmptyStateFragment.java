package com.marcohc.architecture.app.presentation.story.emptystate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marcohc.architecture.app.R;
import com.marcohc.architecture.presentation.view.BaseView;
import com.marcohc.architecture.presentation.view.fragment.BaseMvpFragment;

import butterknife.BindView;

public class EmptyStateFragment extends BaseMvpFragment<EmptyStateView, EmptyStatePresenter> implements BaseView {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************
    @BindView(R.id.noDataContainer)
    ViewGroup noDataContainer;

    @BindView(R.id.noDataImage)
    ImageView noDataImage;

    @BindView(R.id.noDataTitleText)
    TextView noDataTitleText;

    @BindView(R.id.noDataSubtitleText)
    TextView noDataSubtitleText;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @Override
    public int getLayoutRes() {
        return R.layout.empty_state_fragment;
    }

    @NonNull
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
