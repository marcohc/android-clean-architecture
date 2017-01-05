package com.marcohc.architecture.app.presentation.story.user.userlist.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.marcohc.architecture.app.R;
import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.common.util.helper.StringUtils;
import com.marcohc.architecture.presentation.view.adapter.BaseViewHolder;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * View holder of a {@link UserModel}.
 */
public class UserViewHolder extends BaseViewHolder<UserModel> {

    @BindView(R.id.userImageView)
    ImageView mUserImageView;

    @BindView(R.id.usernameTextView)
    TextView mUsernameTextView;

    @BindView(R.id.emailTextView)
    TextView mEmailTextView;

    // Class
    private int mPosition;

    @Override
    public void setUpView(Context context, UserModel model, int position) {
        String image = model.getPictureUrl();
        this.mPosition = position;
        if (!StringUtils.isBlank(image)) {
            Glide.with(context).load(image).error(R.drawable.im_user_place_holder).into(mUserImageView);
        } else {
            mUserImageView.setImageResource(R.drawable.im_user_place_holder);
        }
        mUsernameTextView.setText(model.getName());
        mEmailTextView.setText(model.getEmail());
    }

    // Catch events and pass them through this method
    @OnClick(R.id.userImageView)
    protected void onUserImageClick(View view) {
        onChildViewClick(view, mPosition);
    }

}
