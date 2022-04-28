package be.jevent.eventservice.service;

import be.jevent.eventservice.createresource.CreateEventResource;
import be.jevent.eventservice.dto.EventDTO;
import be.jevent.eventservice.dto.TicketDTO;
import be.jevent.eventservice.exception.EventException;
import be.jevent.eventservice.exception.LocationException;
import be.jevent.eventservice.model.Event;
import be.jevent.eventservice.model.EventType;
import be.jevent.eventservice.model.Location;
import be.jevent.eventservice.repository.EventRepository;
import be.jevent.eventservice.repository.LocationRepository;
import be.jevent.eventservice.service.client.TicketFeignClient;
import be.jevent.eventservice.util.FileUploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final static Logger LOGGER = LoggerFactory.getLogger(EventService.class);
    private static final String TOPIC = "test";

    @Autowired
    private TicketFeignClient ticketFeignClient;

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
        eventDTO.get().setTicketsLeft(eventDTO.get().getTicketsLeft() - retrieveTicketsSold(id));
        return eventDTO.get();
    }

    public String createEvent(CreateEventResource eventResource, Locale locale,
                              MultipartFile banner, MultipartFile thumb) throws IOException {
        String responseMessage;

        if(EventType.forName(eventResource.getEventType()) == null){
            throw new EventException("Event type " + eventResource.getEventType() + " not found");
        }

        Optional<Location> location = locationRepository.findById((long) eventResource.getLocationId());
        if(location.isEmpty()){
            throw new LocationException("Location not found");
        }

        String bannerFile = StringUtils.cleanPath(Objects.requireNonNull(banner.getOriginalFilename()));
        String thumbFile = StringUtils.cleanPath(Objects.requireNonNull(thumb.getOriginalFilename()));

        Event event = new Event();
        event.setEventName(eventResource.getEventName());
        event.setEventType(EventType.valueOf(eventResource.getEventType()));
        event.setShortDescription(eventResource.getShortDescription());
        event.setDescription(eventResource.getDescription());
        event.setEventDate(eventResource.getEventDate());
        event.setEventTime(eventResource.getEventTime());
        event.setLocation(location.get());
        event.setTicketsLeft(eventResource.getAmountOfTickets());
        event.setPrice(eventResource.getPrice());
        event.setBanner(bannerFile);
        event.setThumbnail(thumbFile);

        eventRepository.save(event);

        String uploadBanner = "events/banner-" + event.getId();
        String uploadThumb = "events/thumb-" + event.getId();

        FileUploadUtil.saveFile(uploadBanner, bannerFile, banner);
        FileUploadUtil.saveFile(uploadThumb, thumbFile, thumb);

        responseMessage = String.format(messageSource.getMessage(
                        "event.create.message", null, locale),
                eventResource.toString());
        return responseMessage;
    }

    public String deleteEvent(Long id){
        String responseMessage;

        eventRepository.deleteById(id);

        return "event deleted";
    }

    public void updateEvent(Event event){
        eventRepository.save(event);

    }

    public int retrieveTicketsSold(Long eventId){
        return ticketFeignClient.getTicketsSold(eventId);
    }
}
