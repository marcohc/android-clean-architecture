package com.marcohc.android.clean.architecture.domain.model;

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

}
