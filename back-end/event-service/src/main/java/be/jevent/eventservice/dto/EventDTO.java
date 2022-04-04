package be.jevent.eventservice.dto;

import be.jevent.eventservice.model.Event;
import be.jevent.eventservice.model.Location;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class EventDTO {

    private final Long id;
    private final String eventName;
    private final String eventType;
    private final Location location;

    public EventDTO(Event event){
        this.id = event.getId();
        this.eventName = event.getEventName();
        this.eventType = event.getEventType().getType();
        this.location = event.getLocation();
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

    public Location getLocation() {
        return location;
    }
}
