package com.marcohc.android.clean.architecture.domain.bus.event;

import com.marcohc.android.clean.architecture.common.bus.event.BaseUIEvent;

public class MenuItemClickEvent extends BaseUIEvent {

    private final int position;

    public MenuItemClickEvent(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
