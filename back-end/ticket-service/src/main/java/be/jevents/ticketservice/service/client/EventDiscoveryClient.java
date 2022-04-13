package be.jevents.ticketservice.service.client;

import be.jevents.ticketservice.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Component
public class EventDiscoveryClient {

    private final Logger LOGGER = LoggerFactory.getLogger(EventDiscoveryClient.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    public Event getEvent(Long eventId){
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("event-service");

        if (instances.size() == 0) return null;

        String serviceUri = String.format("%s/events/%s", instances.get(0).getUri().toString(), eventId);

        LOGGER.info(serviceUri);

        ResponseEntity<Event> restExchange =
                restTemplate.exchange(
                        serviceUri,
                        HttpMethod.GET,
                        null, Event.class, eventId);
        Event event = new Event();
        event.setEventName(Objects.requireNonNull(restExchange.getBody()).getEventName());
        return restExchange.getBody();
    }
}
