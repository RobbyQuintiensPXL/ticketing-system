package be.jevents.ticketservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class TicketService {

    private final Logger LOGGER = LoggerFactory.getLogger(TicketService.class);

    @KafkaListener(topics = "test", groupId = "archived")
    public void consume(String message){
        LOGGER.info(String.format("$$ -> Consumed message -> %s", message));
    }
}
