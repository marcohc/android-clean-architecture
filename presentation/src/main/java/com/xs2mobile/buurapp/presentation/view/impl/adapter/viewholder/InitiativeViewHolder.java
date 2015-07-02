package com.xs2mobile.buurapp.presentation.view.impl.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.xs2mobile.buurapp.R;
import com.xs2mobile.buurapp.presentation.model.BaseModel;
import com.xs2mobile.buurapp.presentation.model.InitiativeModel;

import butterknife.ButterKnife;

public class InitiativeViewHolder extends ViewHolderAbstractClass {

    TextView titleText;
    TextView ownerText;
    TextView subOwnerText;

    @Override
    public int getLayout() {
        return R.layout.initiative_list_item;
    }

    @Override
    public void findViewsById(View view) {
        titleText = ButterKnife.findById(view, R.id.titleText);
        ownerText = ButterKnife.findById(view, R.id.ownerText);
        subOwnerText = ButterKnife.findById(view, R.id.subOwnerText);
    }

    @Override
    public void initializeComponentBehavior(BaseModel item, Context context, int position) {
        InitiativeModel model = (InitiativeModel) item;
        titleText.setText(model.getTitle());
        if (model.isMine()) {
            ownerText.setText(R.string.initiative_of);
            subOwnerText.setText(R.string.you);
        } else {
            ownerText.setText(context.getString(R.string.initiative_of) + " " + model.getLocation().getStreet() + " " + model.getLocation().getNumber());
            subOwnerText.setText("");
        }
    }
}