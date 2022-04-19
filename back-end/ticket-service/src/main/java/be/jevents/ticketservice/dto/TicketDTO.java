package be.jevents.ticketservice.dto;

import be.jevents.ticketservice.model.Event;
import be.jevents.ticketservice.model.Ticket;

public class TicketDTO {

    private final Long id;
    private final int eventId;
    private final Event event;

    public TicketDTO(Ticket ticket){
        this.id = ticket.getId();
        this.eventId = ticket.getEventId();
        this.event = ticket.getEvent();
    }

    public Long getId() {
        return id;
    }

    public int getEventId() {
        return eventId;
    }

    public Event getEvent() {
        return event;
    }
}
