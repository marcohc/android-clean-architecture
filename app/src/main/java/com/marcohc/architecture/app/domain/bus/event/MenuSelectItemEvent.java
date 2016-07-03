package com.marcohc.architecture.app.domain.bus.event;

import com.marcohc.architecture.common.bus.BusEvent;

public class MenuSelectItemEvent implements BusEvent {

    private final int position;

    public MenuSelectItemEvent(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
