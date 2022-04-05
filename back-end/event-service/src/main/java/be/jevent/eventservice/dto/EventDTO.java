package be.jevent.eventservice.dto;

import be.jevent.eventservice.model.Event;
import be.jevent.eventservice.model.Location;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventDTO {

    private final Long id;
    private final String eventName;
    private final String eventType;
    private final LocationDTO location;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private final LocalDate eventDate;
    @JsonFormat(pattern = "HH:mm:ss")
    private final LocalTime eventTime;
    private final String description;
    private final String shortDescription;

    public LocationDTO getLocationDTO(Location location) {
        return new LocationDTO(location);
    }

    public EventDTO(Event event) {
        this.id = event.getId();
        this.eventName = event.getEventName();
        this.eventType = event.getEventType().getType();
        this.location = getLocationDTO(event.getLocation());
        this.eventDate = event.getEventDate();
        this.eventTime = event.getEventTime();
        this.description = event.getDescription();
        this.shortDescription = event.getShortDescription();
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

    public LocationDTO getLocation() {
        return location;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public LocalTime getEventTime() {
        return eventTime;
    }

    public String getDescription() {
        return description;
    }

    public String getShortDescription() {
        return shortDescription;
    }
}
