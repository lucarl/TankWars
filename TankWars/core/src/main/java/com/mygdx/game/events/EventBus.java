package com.mygdx.game.events;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 Very simple event bus. All observers can register for events and an
 observable can publish (send) events.

 @author hajo

 */

public enum EventBus {

    BUS;

    private final List<IEventHandler> handlers
            = Collections.synchronizedList(new ArrayList<>());
    private boolean trace = true;

    public void register(IEventHandler handler) {
        handlers.add(handler);
    }

    public void unRegister(IEventHandler handler) {
        handlers.remove(handler);
    }

    public void publish(Event evt) {
        // Tracking all events
        if (trace) {
            System.out.println(evt);
        }
        synchronized (handlers) {
            handlers.stream().forEach((evh) -> {
                evh.onEvent(evt);
            });
        }
    }
}
