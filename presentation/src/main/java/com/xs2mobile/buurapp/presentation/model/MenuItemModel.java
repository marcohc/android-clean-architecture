package com.xs2mobile.buurapp.presentation.model;

import com.xs2mobile.buurapp.presentation.view.impl.adapter.viewholder.MenuViewHolder;
import com.xs2mobile.buurapp.presentation.view.impl.adapter.viewholder.ViewHolderAbstractClass;
import com.xs2mobile.buurapp.presentation.util.Constants;

public class MenuItemModel extends BaseModel {

    private String text;
    private int iconId;

    public MenuItemModel(String text, int iconId) {
        this.text = text;
        this.iconId = iconId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    @Override
    public ViewHolderAbstractClass getDefaultViewHolder(Constants.DATA_TYPE itemType) {
        return new MenuViewHolder();
    }

}
