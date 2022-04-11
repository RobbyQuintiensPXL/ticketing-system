package be.jevents.ticketservice.dto;

import be.jevents.ticketservice.model.Ticket;

public class TicketDTO {

    private final Long id;
    private final int eventId;
    //private final String eventName;
    //private final String eventType;

    public TicketDTO(Ticket ticket){
        this.id = ticket.getId();
        this.eventId = ticket.getEventId();
    }

    public Long getId() {
        return id;
    }

    public int getEventId() {
        return eventId;
    }
}
