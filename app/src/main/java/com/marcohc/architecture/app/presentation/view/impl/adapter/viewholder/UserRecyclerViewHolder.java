package com.marcohc.architecture.app.presentation.view.impl.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.marcohc.architecture.app.R;
import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.common.helper.StringHelper;

import butterknife.Bind;
import butterknife.OnClick;

public class UserRecyclerViewHolder extends BaseRecyclerViewHolder<UserModel> {

    @Bind(R.id.userImage)
    ImageView userImage;

    @Bind(R.id.usernameText)
    TextView usernameText;

    @Bind(R.id.passwordText)
    TextView passwordText;

    public UserRecyclerViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(Context context, UserModel model, int position) {
        String image = "";
        if (!StringHelper.isEmpty(image)) {
            Glide.with(context).load(image).error(R.drawable.im_user_place_holder).into(userImage);
        } else {
            userImage.setImageResource(R.drawable.im_user_place_holder);
        }
        usernameText.setText(model.getName());
        passwordText.setText(model.getPassword());
    }

    @OnClick(R.id.userImage)
    protected void onUserImageClick(View view) {
        onChildViewClick(view);
    }

}