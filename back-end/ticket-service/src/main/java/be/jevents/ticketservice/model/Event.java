package be.jevents.ticketservice.model;

import org.springframework.hateoas.RepresentationModel;


public class Event extends RepresentationModel<Event> {

    private Long id;
    private String eventName;

    public Event(){
        //Empty Constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}


