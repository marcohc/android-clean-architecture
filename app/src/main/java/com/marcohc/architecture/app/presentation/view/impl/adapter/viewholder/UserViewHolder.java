package com.marcohc.architecture.app.presentation.view.impl.adapter.viewholder;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.marcohc.architecture.app.R;
import com.marcohc.architecture.app.domain.model.UserModel;
import com.marcohc.architecture.common.helper.StringHelper;
import com.marcohc.architecture.presentation.view.adapter.ViewHolder;

import butterknife.Bind;

public class UserViewHolder extends ViewHolder<UserModel> {

    @Bind(R.id.userImage)
    ImageView userImage;

    @Bind(R.id.usernameText)
    TextView usernameText;

    @Bind(R.id.passwordText)
    TextView passwordText;

    @Override
    public int getLayout() {
        return R.layout.user_list_item;
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

}