package com.xs2mobile.buurapp.presentation.view.impl.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xs2mobile.buurapp.R;
import com.xs2mobile.buurapp.presentation.model.BaseModel;
import com.xs2mobile.buurapp.presentation.model.MenuItemModel;

import butterknife.ButterKnife;

public class MenuViewHolder extends ViewHolderAbstractClass {

    ImageView menuImage;
    TextView menuText;
    ViewGroup menuItemContainer;

    @Override
    public int getLayout() {
        return R.layout.menu_list_item;
    }

    @Override
    public void findViewsById(View view) {
        menuItemContainer = ButterKnife.findById(view, R.id.menuItemContainer);
        menuImage = ButterKnife.findById(view, R.id.menuImage);
        menuText = ButterKnife.findById(view, R.id.menuText);
    }

    @Override
    public void initializeComponentBehavior(BaseModel item, Context context, int position) {
        MenuItemModel menuItemModel = (MenuItemModel) item;
        menuText.setText(menuItemModel.getText());
        menuImage.setImageResource(menuItemModel.getIconId());
    }

}