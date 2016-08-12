package com.marcohc.architecture.app.presentation.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.marcohc.architecture.app.R;
import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.common.helper.StringHelper;
import com.marcohc.architecture.presentation.view.adapter.BaseRecyclerViewHolder;

import butterknife.Bind;
import butterknife.OnClick;

public class UserRecyclerViewHolder extends BaseRecyclerViewHolder<UserModel> {

    // View
    @Bind(R.id.userImageView)
    ImageView userImageView;

    @Bind(R.id.usernameTextView)
    TextView usernameTextView;

    @Bind(R.id.emailTextView)
    TextView emailTextView;

    public UserRecyclerViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(Context context, UserModel model, int position) {
        String image = model.getPictureUrl();
        if (!StringHelper.isEmpty(image)) {
            Glide.with(context).load(image).error(R.drawable.im_user_place_holder).into(userImageView);
        } else {
            userImageView.setImageResource(R.drawable.im_user_place_holder);
        }
        usernameTextView.setText(model.getName());
        emailTextView.setText(model.getEmail());
    }

    @OnClick(R.id.userImageView)
    protected void onUserImageClick(View view) {
        onChildViewClick(view);
    }

}