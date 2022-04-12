package be.jevents.ticketservice.service;

import be.jevents.ticketservice.model.Event;
import be.jevents.ticketservice.repository.TicketRepository;
import be.jevents.ticketservice.service.client.EventDiscoveryClient;
import be.jevents.ticketservice.service.client.EventFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class TicketService {

    private final Logger LOGGER = LoggerFactory.getLogger(TicketService.class);

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventDiscoveryClient eventDiscoveryClient;

    @Autowired
    private EventFeignClient feignClient;

    private Event event;

    public Event getEventInfo(Long eventId){
        event = feignClient.getEvent(eventId);
        return event;
    }


    @KafkaListener(topics = "test", groupId = "archived")
    public void consume(String message){
        LOGGER.info(String.format("$$ -> Consumed message -> %s", message));
    }


}
