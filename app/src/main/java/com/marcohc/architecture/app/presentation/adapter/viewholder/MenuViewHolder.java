package com.marcohc.architecture.app.presentation.adapter.viewholder;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.marcohc.architecture.app.R;
import com.marcohc.architecture.app.domain.model.MenuItemModel;
import com.marcohc.architecture.presentation.view.adapter.BaseViewHolder;

import butterknife.Bind;

public class MenuViewHolder extends BaseViewHolder<MenuItemModel> {

    @Bind(R.id.menuImage)
    ImageView menuImage;

    @Bind(R.id.menuText)
    TextView menuText;

    @Override
    public void setUpView(Context context, MenuItemModel model, int position) {
        menuText.setText(model.getText());
        menuImage.setImageResource(model.getIconId());
    }

}