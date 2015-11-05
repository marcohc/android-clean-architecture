package com.marcohc.android.clean.architecture.presentation.view.impl.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.marcohc.android.clean.architecture.domain.model.BaseModel;
import com.marcohc.android.clean.architecture.domain.model.UserModel;
import com.marcohc.android.clean.architecture.presentation.R;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;

public class UserViewHolder extends ViewHolderAbstractClass {

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
    public void initializeComponentBehavior(BaseModel item, Context context, int position) {

        UserModel model = (UserModel) item;

        String image = model.getPicture().getThumbnail();
        if (!StringHelper.isEmpty(image)) {
            Picasso.with(context).load(image).error(R.drawable.im_user_place_holder).fit().into(userImage);
        } else {
            userImage.setImageResource(R.drawable.im_user_place_holder);
        }
        usernameText.setText(model.getUsername());
        passwordText.setText(model.getPassword());
    }
}