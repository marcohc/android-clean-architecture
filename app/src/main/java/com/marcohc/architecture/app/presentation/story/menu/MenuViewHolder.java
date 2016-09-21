package com.marcohc.architecture.app.presentation.story.menu;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.marcohc.architecture.app.R;
import com.marcohc.architecture.app.domain.model.MenuItemModel;
import com.marcohc.architecture.presentation.view.adapter.BaseViewHolder;

import butterknife.BindView;

public class MenuViewHolder extends BaseViewHolder<MenuItemModel> {

    @BindView(R.id.menuImage)
    ImageView menuImage;

    @BindView(R.id.menuText)
    TextView menuText;

    @Override
    public void setUpView(Context context, MenuItemModel model, int position) {
        menuText.setText(model.getText());
        menuImage.setImageResource(model.getIconId());
    }

}