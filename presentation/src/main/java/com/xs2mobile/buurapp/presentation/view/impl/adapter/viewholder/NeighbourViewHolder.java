package com.xs2mobile.buurapp.presentation.view.impl.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xs2mobile.buurapp.R;
import com.xs2mobile.buurapp.presentation.model.BaseModel;
import com.xs2mobile.buurapp.presentation.model.NeighbourChatModel;
import com.xs2mobile.buurapp.presentation.model.UserModel;

import org.apache.commons.lang3.StringUtils;

import butterknife.ButterKnife;

public class NeighbourViewHolder extends ViewHolderAbstractClass {

    ImageView userImage;
    TextView firstNameText;
    TextView addressText;
    ViewGroup menuItemContainer;
    ViewGroup countContainer;
    TextView countText;
    ImageView countImage;

    @Override
    public int getLayout() {
        return R.layout.neighbours_list_item;
    }

    @Override
    public void findViewsById(View view) {
        menuItemContainer = ButterKnife.findById(view, R.id.menuItemContainer);
        userImage = ButterKnife.findById(view, R.id.userImage);
        firstNameText = ButterKnife.findById(view, R.id.firstNameText);
        addressText = ButterKnife.findById(view, R.id.addressText);
        countContainer = ButterKnife.findById(view, R.id.countContainer);
        countImage = ButterKnife.findById(view, R.id.countImage);
        countText = ButterKnife.findById(view, R.id.countText);
    }

    @Override
    public void initializeComponentBehavior(BaseModel item, Context context, int position) {

        NeighbourChatModel model = (NeighbourChatModel) item;
        UserModel user = model.getUser();

        String image = user.getImage();
        if (!StringUtils.isBlank(image)) {
            Picasso.with(context).load(image).error(R.drawable.im_user_place_holder).fit().into(userImage);
        } else {
            userImage.setImageResource(R.drawable.im_user_place_holder);
        }

        firstNameText.setText(user.getFullName());
        addressText.setText(user.getAddress().getStreet() + " " + user.getAddress().getNumber());

        if (model.getChatId() != null && model.getCount() > 0) {
            countText.setText(String.valueOf(model.getCount()));
            countContainer.setVisibility(View.VISIBLE);
        } else {
            countContainer.setVisibility(View.GONE);
        }
    }

}