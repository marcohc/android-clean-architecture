package com.marcohc.architecture.presentation.view.impl.fragment;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.marcohc.architecture.presentation.presenter.impl.EmptyStatePresenterImpl;
import com.marcohc.architecture.presentation.presenter.inter.EmptyStatePresenter;
import com.marcohc.architecture.presentation.view.BaseView;
import com.marcohc.architecture.presentation.view.fragment.BaseMvpFragment;
import com.marcohc.architecture.presentation.view.inter.EmptyStateView;
import com.marcohc.architecture.sample.R;

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
            if (noDataContainer.getVisibility() != View.VISIBLE) {
                noDataContainer.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.Pulse).duration(1200).playOn(noDataContainer);
            }
        } else {
            if (noDataContainer.getVisibility() == View.VISIBLE) {
                noDataContainer.setVisibility(View.GONE);
                YoYo.with(Techniques.FadeOut).withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (noDataContainer != null) {
                            noDataContainer.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                }).duration(250).playOn(noDataContainer);
            }
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
