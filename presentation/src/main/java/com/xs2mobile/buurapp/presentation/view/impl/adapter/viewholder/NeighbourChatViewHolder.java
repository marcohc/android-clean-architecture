package com.xs2mobile.buurapp.presentation.view.impl.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xs2mobile.buurapp.R;
import com.xs2mobile.buurapp.presentation.model.BaseModel;
import com.xs2mobile.buurapp.presentation.model.MessageModel;

import org.apache.commons.lang3.StringUtils;

import butterknife.ButterKnife;

public class NeighbourChatViewHolder extends ViewHolderAbstractClass {

    ImageView userImage;
    TextView messageText;
    TextView timeText;
    ViewGroup balloonContainer;

    @Override
    public int getLayout() {
        return R.layout.neighbour_chat_list_item;
    }

    @Override
    public void findViewsById(View view) {
        userImage = ButterKnife.findById(view, R.id.userImage);
        messageText = ButterKnife.findById(view, R.id.messageText);
        timeText = ButterKnife.findById(view, R.id.timeText);
        balloonContainer = ButterKnife.findById(view, R.id.balloonContainer);
    }

    @Override
    public void initializeComponentBehavior(BaseModel item, Context context, int position) {

        MessageModel model = (MessageModel) item;

        if (!StringUtils.isBlank(model.getUser().getImage())) {
            Picasso.with(context).load(model.getUser().getImage()).error(R.drawable.im_user_place_holder).fit().into(userImage);
        } else {
            userImage.setImageResource(R.drawable.im_user_place_holder);
        }

        messageText.setText(model.getText());
        timeText.setText(model.getDateFormatted());

        int smallPadding = (int) context.getResources().getDimension(R.dimen.small_padding);
        int padding = (int) context.getResources().getDimension(R.dimen.padding);
        int bigPadding = (int) context.getResources().getDimension(R.dimen.big_padding);

        if (model.isMine()) {

            balloonContainer.setBackgroundResource(R.drawable.im_bubble_right);

            RelativeLayout.LayoutParams relativeLayout = (RelativeLayout.LayoutParams) userImage.getLayoutParams();
            relativeLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
            relativeLayout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            userImage.setLayoutParams(relativeLayout);

            relativeLayout = (RelativeLayout.LayoutParams) balloonContainer.getLayoutParams();
            relativeLayout.addRule(RelativeLayout.RIGHT_OF, 0);
            relativeLayout.addRule(RelativeLayout.LEFT_OF, R.id.userImage);
            balloonContainer.setLayoutParams(relativeLayout);

            LinearLayout.LayoutParams linearLayout = (LinearLayout.LayoutParams) messageText.getLayoutParams();
            linearLayout.setMargins(smallPadding, 0, bigPadding, smallPadding);
            messageText.setLayoutParams(linearLayout);

            relativeLayout = (RelativeLayout.LayoutParams) timeText.getLayoutParams();
            relativeLayout.setMargins(smallPadding, 0, bigPadding, 0);
            timeText.setLayoutParams(relativeLayout);

        } else {

            balloonContainer.setBackgroundResource(R.drawable.im_bubble_left);

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) userImage.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            userImage.setLayoutParams(params);

            params = (RelativeLayout.LayoutParams) balloonContainer.getLayoutParams();
            params.addRule(RelativeLayout.LEFT_OF, 0);
            params.addRule(RelativeLayout.RIGHT_OF, R.id.userImage);
            balloonContainer.setLayoutParams(params);

            LinearLayout.LayoutParams linearLayout = (LinearLayout.LayoutParams) messageText.getLayoutParams();
            linearLayout.setMargins(bigPadding, 0, bigPadding, smallPadding);
            messageText.setLayoutParams(linearLayout);

            params = (RelativeLayout.LayoutParams) timeText.getLayoutParams();
            params.setMargins(0, 0, smallPadding, 0);
            timeText.setLayoutParams(params);
        }
    }
}