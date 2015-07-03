package com.marcohc.android.clean.architecture.domain.interactor.inter;

import com.marcohc.android.clean.architecture.common.bus.event.Event;

public interface UseCase {

    void execute();

    Event createEvent();

    Event createResponse();

}
