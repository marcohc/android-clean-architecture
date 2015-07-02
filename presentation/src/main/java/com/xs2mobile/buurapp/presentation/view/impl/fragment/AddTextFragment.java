package com.xs2mobile.buurapp.presentation.view.impl.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.marcohc.helperoid.ScreenHelper;
import com.xs2mobile.buurapp.R;
import com.xs2mobile.buurapp.presentation.presenter.impl.AddTextPresenterImpl;
import com.xs2mobile.buurapp.presentation.presenter.inter.AddTextPresenter;
import com.xs2mobile.buurapp.presentation.view.inter.AddTextView;

import org.apache.commons.lang3.StringUtils;

import butterknife.InjectView;
import butterknife.OnClick;

public class AddTextFragment extends BaseMvpFragment<AddTextView, AddTextPresenter> implements AddTextView {

    // ************************************************************************************************************************************************************************
    // * Attributes
    // ************************************************************************************************************************************************************************
    @InjectView(R.id.addCommentText)
    EditText addCommentText;

    private AddTextFragmentListener listener;

    // ************************************************************************************************************************************************************************
    // * Initialization methods
    // ************************************************************************************************************************************************************************

    @Override
    protected int getLayoutRes() {
        return R.layout.add_text_fragment;
    }

    @Override
    public AddTextPresenter createPresenter() {
        return new AddTextPresenterImpl();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        initializeComponentBehavior();
    }

    public void initializeComponentBehavior() {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    // ************************************************************************************************************************************************************************
    // * Event handler methods
    // ************************************************************************************************************************************************************************

    @OnClick(R.id.addTextImage)
    protected void onAddTextImageClick() {
        String text = addCommentText.getText().toString();
        if (!StringUtils.isBlank(text)) {
            if (listener != null) {
                listener.onMessageAdded(text);
            }
            addCommentText.setText("");
            ScreenHelper.hideKeyboard(getActivity());
        }
    }

    public void setHintText(String text) {
        addCommentText.setHint(text);
    }

    // ************************************************************************************************************************************************************************
    // * View interface methods
    // ************************************************************************************************************************************************************************

    // ************************************************************************************************************************************************************************
    // * UI methods
    // ************************************************************************************************************************************************************************

    public interface AddTextFragmentListener {
        void onMessageAdded(String text);
    }

    public void setListener(AddTextFragmentListener listener) {
        this.listener = listener;
    }
}
