package be.jevents.ticketservice.service;

import be.jevents.ticketservice.model.Event;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "events", groupId = "event_json")
    public void consumeJson(Event event) {
        System.out.println("Consumed JSON Message: " + event);
    }
}
