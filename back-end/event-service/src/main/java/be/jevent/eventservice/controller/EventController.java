package be.jevent.eventservice.controller;

import be.jevent.eventservice.createresource.CreateEventResource;
import be.jevent.eventservice.dto.EventDTO;
import be.jevent.eventservice.model.EventType;
import be.jevent.eventservice.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "events")
@CrossOrigin(origins = "http://localhost:4200")
public class EventController {

    @Autowired
    private EventService eventService;


    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        return new ResponseEntity<>(eventService.getAllEvents(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable("id") Long id){
        return new ResponseEntity<>(eventService.getEventById(id), HttpStatus.OK);
    }

    @GetMapping("/search/{type}")
    public ResponseEntity<List<EventDTO>> getEventsByType(@PathVariable("type") String type){
        return new ResponseEntity<>(eventService.getAllEventsByType(EventType.valueOf(type.toUpperCase())), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<String> createEvent(@RequestBody @Valid CreateEventResource eventResource,
                                              @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return new ResponseEntity<>(eventService.createEvent(eventResource, locale), HttpStatus.CREATED);
    }

    @GetMapping("/archive")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message){
        eventService.sendMessage(message);
    }


}
