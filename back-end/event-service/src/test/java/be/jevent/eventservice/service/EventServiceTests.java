package be.jevent.eventservice.service;

import be.jevent.eventservice.dto.EventDTO;
import be.jevent.eventservice.exception.EventException;
import be.jevent.eventservice.model.Event;
import be.jevent.eventservice.model.EventType;
import be.jevent.eventservice.model.Location;
import be.jevent.eventservice.repository.EventPageRepository;
import be.jevent.eventservice.repository.EventRepository;
import be.jevent.eventservice.repository.LocationRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EventServiceTests {

    @MockBean
    private EventRepository eventRepository;

    @MockBean
    private EventPageRepository eventPageRepository;

    @MockBean
    private LocationRepository locationRepository;

    @Mock
    private FileStorageService fileStorageService;

    @Autowired
    private EventService eventService;

    private Event event;
    private Location location;

    Predicate predicate;
    BooleanBuilder builder;

    public void init() {
        builder = new BooleanBuilder();

        event = new Event();
        location = new Location();
        location.setId(1L);
        location.setBuildingName("Building");
        location.setCity("City");
        event.setEventName("Test");
        event.setEventType(EventType.CULTURE);
        event.setId(1L);
        event.setDescription("Description");
        event.setShortDescription("Short Description");
        event.setEventDate(LocalDate.now());
        event.setEventTime(LocalTime.now());
        event.setTicketsLeft(200);
        event.setPrice(100);
        event.setAccepted(true);
        event.setLocation(location);
        event.setTicketOffice("Organisation");
        event.setBanner("banner");
        event.setThumbnail("thumb");
    }

//    @Test
//    public void getAllEventsTest() {
//        init();
//        List<Event> eventList = new LinkedList<>();
//        eventList.add(event);
//        Page<Event> events = new PageImpl<>(eventList);
//        Pageable paging = PageRequest.of(0, 5);
//
//        when(eventPageRepository.findAll(paging)).thenReturn(events);
//        Page<EventDTO> eventDTOList = eventService.getAllEventsImpl(paging.getPageNumber(), paging.getPageSize());
//
//        List<EventDTO> eventDTOS = eventDTOList.get().collect(Collectors.toList());
//
//        assertEquals(eventDTOS.size(), eventList.size());
//        assertEquals(eventDTOS.get(0).getBanner(), event.getBanner());
//        assertEquals(eventDTOS.get(0).getThumbnail(), event.getThumbnail());
//    }

    @Test
    public void getAllEventsBySearchTerms() {
        init();
        List<Event> eventList = new LinkedList<>();
        eventList.add(event);
        Page<Event> events = new PageImpl<>(eventList);
        Pageable paging = PageRequest.of(0, 5);

        when(eventPageRepository.findAll(builder.and(predicate), paging)).thenReturn(events);
        Page<EventDTO> eventDTOList = eventService.findByTypeCity(predicate, paging.getPageNumber(), paging.getPageSize());

        List<EventDTO> eventDTOS = eventDTOList.get().collect(Collectors.toList());

        assertEquals(eventDTOS.size(), eventList.size());
        assertEquals(eventDTOS.get(0).getBanner(), event.getBanner());
        assertEquals(eventDTOS.get(0).getThumbnail(), event.getThumbnail());
    }

//    @Test
//    public void createEventTest() throws IOException, FileUploadException {
//        init();
//        when(eventRepository.save(any(Event.class))).thenReturn(event);
//        when(locationRepository.findById(any())).thenReturn(Optional.ofNullable(location));
//
//        String banner = "banner.jpg";
//        String thumb = "thumb.jpg";
//        MockMultipartFile fileBanner = new MockMultipartFile("banner",banner,
//                "text/plain", "test data".getBytes());
//        MockMultipartFile fileThumb = new MockMultipartFile("thumb",thumb,
//                "text/plain", "test data".getBytes());
//
//        CreateEventResource eventResource =
//                new CreateEventResource(event.getEventName(), event.getEventType().getType(), event.getShortDescription(),
//                        event.getDescription(), event.getEventDate(), event.getEventTime(),
//                        location.getId().toString(), event.getPrice(), event.getTicketsLeft(),
//                        banner, thumb);
//
//        fileStorageService.save(fileBanner);
//        fileStorageService.save(fileThumb);
//
//        eventService.createEvent(eventResource, fileBanner, fileThumb, anyString());
//    }

//    @Test
//    public void getEventByIdtest(){
//        init();
//
//        when(eventRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(event));
//
//        EventDTO eventDTO = eventService.getEventById(event.getId());
//
//        assertEquals(eventDTO.getDescription(), event.getDescription());
//        assertEquals(eventDTO.getShortDescription(), event.getShortDescription());
//    }

    @Test
    public void getAllEventsFromTicketOfficeTest() {
        init();
        List<Event> eventList = new LinkedList<>();
        eventList.add(event);
        Page<Event> events = new PageImpl<>(eventList);
        Pageable paging = PageRequest.of(0, 5);

        when(eventPageRepository.findAll(any(Predicate.class), any(Pageable.class))).thenReturn(events);
        Page<EventDTO> eventDTOList = eventService.getAllEventsFromTicketOffice(predicate, event.getTicketOffice(), paging.getPageNumber(), paging.getPageSize());
        List<EventDTO> eventDTOS = eventDTOList.get().collect(Collectors.toList());

        assertEquals(eventDTOS.size(), eventList.size());
        assertEquals(eventDTOS.get(0).getTicketsLeft(), event.getTicketsLeft());
        assertEquals(eventDTOS.get(0).getId(), event.getId());
        assertEquals(eventDTOS.get(0).getEventType(), event.getEventType().getType());
    }

    @Test
    public void throwExceptionEventByIdNotFound() {
        Long id = 2L;
        Throwable thrown = assertThrows(EventException.class, () -> eventService.getEventById(id));
        assertEquals("Event not found", thrown.getMessage());
    }

  /*  @Test
    public void throwExceptionNoEventsFound() {
        Pageable paging = PageRequest.of(0, 5);
        Throwable thrown = assertThrows(EventException.class, () -> eventService.getAllEvents(paging.getPageNumber(), paging.getPageSize()));
        assertEquals("No events found", thrown.getMessage());
    }*/


/*    @Test
    public void throwExceptionNoEventsFoundFromTicketOffice() {
        Throwable thrown = assertThrows(EventException.class, () -> eventService.getAllEventsFromTicketOffice(any(Predicate.class), anyString(), anyInt(), anyInt()));
        assertEquals("No events found", thrown.getMessage());
    }*/


}
