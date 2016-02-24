package com.marcohc.architecture.domain.bus.event;

import com.marcohc.architecture.common.bus.events.ui.BaseUIEvent;

public class MenuItemClickEvent extends BaseUIEvent {

    private final int position;

    public MenuItemClickEvent(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
