package be.jevent.eventservice.service;

import be.jevent.eventservice.createresource.CreateEventResource;
import be.jevent.eventservice.dto.EventDTO;
import be.jevent.eventservice.exception.EventException;
import be.jevent.eventservice.exception.LocationException;
import be.jevent.eventservice.model.Event;
import be.jevent.eventservice.model.EventType;
import be.jevent.eventservice.model.Location;
import be.jevent.eventservice.repository.EventRepository;
import be.jevent.eventservice.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private MessageSource messageSource;

    public List<EventDTO> getAllEvents(){
        List<EventDTO> eventDTOList = eventRepository.findAll().stream().map(EventDTO::new).collect(Collectors.toList());
        if(eventDTOList.isEmpty()){
            throw new EventException("No events found");
        }
        return eventDTOList;
    }

    public List<EventDTO> getAllEventsByType(EventType type){
        List<EventDTO> eventDTOList = eventRepository.findAllByEventType(type).stream().map(EventDTO::new).collect(Collectors.toList());
        if(eventDTOList.isEmpty()){
            throw new EventException("No events found for " + type.getType());
        }
        return eventDTOList;
    }

    public List<EventDTO> getAllEventsByTypeAndCity(EventType type, String city){
        List<EventDTO> eventDTOList = eventRepository.findAllByEventType_AndLocation_City(type, city)
                .stream().map(EventDTO::new).collect(Collectors.toList());
        if(eventDTOList.isEmpty()){
            throw new EventException("No events found for " + type.getType() + " in " + city);
        }
        return eventDTOList;
    }

    public EventDTO getEventById(Long id){
        Optional<EventDTO> eventDTO = eventRepository.findById(id).map(EventDTO::new);
        if(eventDTO.isEmpty()){
            throw new EventException("Event not found");
        }
        return eventDTO.get();
    }

    public String createEvent(CreateEventResource eventResource, Locale locale){
        String responseMessage;

        if(EventType.forName(eventResource.getEventType()) == null){
            throw new EventException("Event type " + eventResource.getEventType() + " not found");
        }

        Optional<Location> location = locationRepository.findById((long) eventResource.getLocationId());
        if(location.isEmpty()){
            throw new LocationException("Location not found");
        }

        responseMessage = String.format(messageSource.getMessage(
                "event.create.message", null, locale),
                eventResource.toString());

        Event event = new Event();
        event.setEventName(eventResource.getEventName());
        event.setEventType(EventType.valueOf(eventResource.getEventType()));
        event.setShortDescription(eventResource.getShortDescription());
        event.setDescription(eventResource.getDescription());
        event.setEventDate(eventResource.getEventDate());
        event.setEventTime(eventResource.getEventTime());
        event.setLocation(location.get());

        eventRepository.save(event);

        return responseMessage;
    }

}
