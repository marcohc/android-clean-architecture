package com.marcohc.android.clean.architecture.domain.bus.event;

import com.marcohc.android.clean.architecture.common.bus.events.ui.BaseUIEvent;

public class MenuSelectItemEvent extends BaseUIEvent {

    private final int position;

    public MenuSelectItemEvent(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
