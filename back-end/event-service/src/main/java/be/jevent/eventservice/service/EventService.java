package be.jevent.eventservice.service;

import be.jevent.eventservice.createresource.CreateEventResource;
import be.jevent.eventservice.dto.EventDTO;
import be.jevent.eventservice.exception.EventException;
import be.jevent.eventservice.model.Event;
import be.jevent.eventservice.model.EventType;
import be.jevent.eventservice.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private MessageSource messageSource;

    public List<EventDTO> getAllEvents(){
        List<EventDTO> eventDTOList = eventRepository.findAll().stream().map(EventDTO::new).collect(Collectors.toList());
        if(eventDTOList.isEmpty()){
            throw new EventException("No events found");
        }
        return eventDTOList;
    }

    public String createEvent(CreateEventResource eventResource, Locale locale){
        String responseMessage;
        if(EventType.forName(eventResource.getEventType()) == null){
            throw new EventException("Event type " + eventResource.getEventType() + " not found");
        }
        responseMessage = String.format(messageSource.getMessage(
                "event.create.message", null, locale),
                eventResource.toString());
        Event event = new Event();
        event.setEventName(eventResource.getEventName());
        event.setEventType(EventType.valueOf(eventResource.getEventType()));

        eventRepository.save(event);

        return responseMessage;
    }

}
