package be.jevent.eventservice.dto;

import be.jevent.eventservice.model.Event;

public class EventDTO {

    private final Long id;
    private final String eventName;
    private final String eventType;

    public EventDTO(Event event){
        this.id = event.getId();
        this.eventName = event.getEventName();
        this.eventType = event.getEventType().getType();
    }

    public Long getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventType() {
        return eventType;
    }
}
