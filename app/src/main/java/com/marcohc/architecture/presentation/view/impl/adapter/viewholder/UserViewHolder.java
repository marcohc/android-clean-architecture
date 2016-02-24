package com.marcohc.architecture.presentation.view.impl.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.marcohc.architecture.domain.model.UserModel;
import com.marcohc.architecture.sample.R;
import com.marcohc.architecture.presentation.view.adapter.ViewHolderAbstractClass;
import com.marcohc.helperoid.StringHelper;

import butterknife.ButterKnife;

public class UserViewHolder extends ViewHolderAbstractClass<UserModel> {

    ImageView userImage;
    TextView usernameText;
    TextView passwordText;

    @Override
    public int getLayout() {
        return R.layout.user_list_item;
    }

    @Override
    public void findViewsById(View view) {
        userImage = ButterKnife.findById(view, R.id.userImage);
        usernameText = ButterKnife.findById(view, R.id.usernameText);
        passwordText = ButterKnife.findById(view, R.id.passwordText);
    }

    @Override
    public void setUpView(Context context, UserModel model, int position) {

        String image = model.getPicture().getThumbnail();
        if (!StringHelper.isEmpty(image)) {
            Glide.with(context).load(image).error(R.drawable.im_user_place_holder).into(userImage);
        } else {
            userImage.setImageResource(R.drawable.im_user_place_holder);
        }
        usernameText.setText(model.getUsername());
        passwordText.setText(model.getPassword());
    }

}