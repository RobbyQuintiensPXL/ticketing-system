package be.jevents.ticketservice.service;

import be.jevents.ticketservice.createresource.CreateTicketResource;
import be.jevents.ticketservice.dto.TicketDTO;
import be.jevents.ticketservice.events.source.SimpleSourceBean;
import be.jevents.ticketservice.exception.TicketException;
import be.jevents.ticketservice.model.Event;
import be.jevents.ticketservice.model.Ticket;
import be.jevents.ticketservice.repository.TicketRepository;
import be.jevents.ticketservice.service.client.EventFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TicketService {

    private final Logger LOGGER = LoggerFactory.getLogger(TicketService.class);

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventFeignClient feignClient;

    @Autowired
    SimpleSourceBean simpleSourceBean;

    private Event event;

    public Event getEventInfo(Long eventId){
        event = feignClient.getEvent(eventId);
        return event;
    }

    public TicketDTO getTicketInfo(Long ticketId){
        Optional<Ticket> foundTicket = ticketRepository.findById(ticketId);
        if (foundTicket.isEmpty()){
            throw new TicketException("Ticket not found");
        }

        Long id = foundTicket.get().getId();
        Event event = feignClient.getEvent(id);
        foundTicket.get().setEvent(event);
        return foundTicket.map(TicketDTO::new).get();
    }

    public int getSoldTicketsAmountForEvent(Long eventId){
        List<TicketDTO> ticketList = ticketRepository.findTicketsByEventId(eventId).stream().map(TicketDTO::new).collect(Collectors.toList());
        if (ticketList.isEmpty()){
            return 0;
        }
        return ticketList.size();
    }

    public void createTicket(){
        
    }
}
