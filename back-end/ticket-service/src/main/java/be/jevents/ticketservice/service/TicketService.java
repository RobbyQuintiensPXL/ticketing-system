package be.jevents.ticketservice.service;

import be.jevents.ticketservice.exception.TicketException;
import be.jevents.ticketservice.model.Event;
import be.jevents.ticketservice.model.Ticket;
import be.jevents.ticketservice.repository.TicketRepository;
import be.jevents.ticketservice.service.client.EventFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TicketService {

    private final Logger LOGGER = LoggerFactory.getLogger(TicketService.class);

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventFeignClient feignClient;

    private Event event;

    public Event getEventInfo(Long eventId){
        event = feignClient.getEvent(eventId);
        return event;
    }

    public Ticket getTicketInfo(Long ticketId){
        Optional<Ticket> foundTicket = ticketRepository.findById(ticketId);
        if (foundTicket.isEmpty()){
            throw new TicketException("Ticket not found");
        }

        Long id = foundTicket.get().getId();

        Event event = feignClient.getEvent(id);

        foundTicket.get().setEvent(event);

        return foundTicket.get();
    }
//
//
//    @KafkaListener(topics = "test", groupId = "archived")
//    public void consume(String message){
//        LOGGER.info(String.format("$$ -> Consumed message -> %s", message));
//    }


}
