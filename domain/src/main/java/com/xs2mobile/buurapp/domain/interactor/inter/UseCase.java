package com.xs2mobile.buurapp.domain.interactor.inter;

import com.xs2mobile.buurapp.domain.bus.event.Event;

public interface UseCase {

    void execute();

    Event createEvent();

    Event createResponse();

}
