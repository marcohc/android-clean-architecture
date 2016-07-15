package com.marcohc.architecture.app.domain.model;

import com.marcohc.architecture.domain.model.BaseJsonModel;

public class MenuItemModel extends BaseJsonModel {

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

}
