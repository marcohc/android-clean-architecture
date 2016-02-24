package com.marcohc.architecture.presentation.view.impl.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marcohc.architecture.sample.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TutorialImageFragment extends Fragment {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************

    protected ImageView backgroundImage;
    protected ImageView foregroundImage;

    @Bind(R.id.tutorialImage)
    ImageView tutorialImage;

    @Bind(R.id.tutorialText)
    TextView tutorialText;

    // Class
    protected int position;

    public final static Integer[] textsIds = new Integer[]{R.string.tutorial_1, R.string.tutorial_2, R.string.tutorial_3, R.string.tutorial_4};
    public final static Integer[] imagesIds = new Integer[]{R.drawable.im_tutorial, R.drawable.im_tutorial, R.drawable.im_tutorial, R.drawable.im_tutorial};

    public static TutorialImageFragment getInstance(int position) {
        TutorialImageFragment fragment = new TutorialImageFragment();
        fragment.position = position;
        return fragment;
    }

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tutorial_image_fragment, container, false);
        ButterKnife.bind(this, view);
        initializeComponentBehavior();
        return view;
    }

    private void initializeComponentBehavior() {
        tutorialImage.setImageResource(imagesIds[position]);
        tutorialText.setText(textsIds[position]);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbindDrawables(backgroundImage);
        unbindDrawables(foregroundImage);
        System.gc();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbindDrawables(backgroundImage);
        unbindDrawables(foregroundImage);
        System.gc();
    }

    private void unbindDrawables(ImageView image) {
        if (image != null && image.getDrawable() != null) {
            image.setImageDrawable(null);
        }
    }
}
