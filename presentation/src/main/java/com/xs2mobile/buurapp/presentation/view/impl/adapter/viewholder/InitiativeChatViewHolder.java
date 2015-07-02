package com.xs2mobile.buurapp.presentation.view.impl.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xs2mobile.buurapp.R;
import com.xs2mobile.buurapp.presentation.model.BaseModel;
import com.xs2mobile.buurapp.presentation.model.MessageModel;

import org.apache.commons.lang3.StringUtils;

import butterknife.ButterKnife;

public class InitiativeChatViewHolder extends ViewHolderAbstractClass {

    ImageView userImage;
    TextView firstNameText;
    TextView messageText;
    TextView timeText;

    @Override
    public int getLayout() {
        return R.layout.initiative_chat_list_item;
    }

    @Override
    public void findViewsById(View view) {
        userImage = ButterKnife.findById(view, R.id.userImage);
        firstNameText = ButterKnife.findById(view, R.id.firstNameText);
        messageText = ButterKnife.findById(view, R.id.messageText);
        timeText = ButterKnife.findById(view, R.id.timeText);
    }

    @Override
    public void initializeComponentBehavior(BaseModel item, Context context, int position) {
        MessageModel model = (MessageModel) item;
        if (!StringUtils.isBlank(model.getUser().getImage())) {
            Picasso.with(context).load(model.getUser().getImage()).error(R.drawable.im_user_place_holder).fit().into(userImage);
        } else {
            userImage.setImageResource(R.drawable.im_user_place_holder);
        }
        firstNameText.setText(model.getUser().getFullName());
        messageText.setText(model.getText());
        timeText.setText(model.getDateFormatted());
    }

}