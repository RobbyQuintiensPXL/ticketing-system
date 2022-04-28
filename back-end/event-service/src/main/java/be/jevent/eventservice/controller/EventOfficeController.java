package be.jevent.eventservice.controller;

import be.jevent.eventservice.createresource.CreateEventResource;
import be.jevent.eventservice.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Locale;

@RestController
@RequestMapping(value = "office")
public class EventOfficeController {

    @Autowired
    private EventService eventService;

    @PostMapping(value = "/event/post", consumes = { "multipart/form-data" })
    public ResponseEntity<String> createEvent(@RequestBody @Valid CreateEventResource eventResource,
                                              @RequestHeader(value = "Accept-Language", required = false) Locale locale,
                                              @RequestParam("banner") MultipartFile banner,
                                              @RequestParam("thumb") MultipartFile thumb) throws IOException {
        return new ResponseEntity<>(eventService.createEvent(eventResource, locale, banner, thumb), HttpStatus.CREATED);
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable("id") Long id){
        return new ResponseEntity<>(eventService.deleteEvent(id), HttpStatus.OK);
    }
}
