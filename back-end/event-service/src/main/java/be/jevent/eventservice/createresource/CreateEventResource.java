package be.jevent.eventservice.createresource;

import com.sun.istack.NotNull;

public class CreateEventResource {

    @NotNull
    private final String eventName;

    @NotNull
    private final String eventType;

    public CreateEventResource(@NotNull String eventName, @NotNull String eventType){
        this.eventName = eventName;
        this.eventType = eventType;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventType() {
        return eventType.toUpperCase();
    }
}
