package com.marcohc.architecture.app.domain.bus.event;

import com.marcohc.architecture.common.bus.BusEvent;

public class MenuItemClickEvent implements BusEvent {

    private final int position;

    public MenuItemClickEvent(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
