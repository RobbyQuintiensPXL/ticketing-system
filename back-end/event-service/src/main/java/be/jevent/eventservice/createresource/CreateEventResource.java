package be.jevent.eventservice.createresource;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.sun.istack.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class CreateEventResource {

    @NotNull
    private final String eventName;

    @NotNull
    private final String eventType;

    @NotNull
    private final String shortDescription;

    @NotNull
    private final String description;

    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private final LocalDate eventDate;

    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private final LocalTime eventTime;

    @NotNull
    private final int locationId;


    public CreateEventResource(@NotNull String eventName, @NotNull String eventType, @NotNull String shortDescription,
                               @NotNull String description, @NotNull LocalDate eventDate, @NotNull LocalTime eventTime,
                               @NotNull int locationId){
        this.eventName = eventName;
        this.eventType = eventType;
        this.shortDescription = shortDescription;
        this.description = description;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.locationId = locationId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventType() {
        return eventType.toUpperCase();
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public LocalTime getEventTime() {
        return eventTime;
    }

    public int getLocationId() {
        return locationId;
    }

    @Override
    public String toString() {
        return "CreateEventResource{" +
                "eventName='" + eventName + '\'' +
                ", eventType='" + eventType + '\'' +
                ", eventDate=" + eventDate +
                ", eventTime=" + eventTime +
                ", location='" + locationId + '\'' +
                '}';
    }
}
