package be.jevent.eventservice.controller;

import be.jevent.eventservice.createresource.CreateEventResource;
import be.jevent.eventservice.dto.EventDTO;
import be.jevent.eventservice.service.EventService;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.oauth2.jwt.Jwt;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "office")
public class EventOfficeController {

    private final EventService eventService;

    public EventOfficeController(EventService eventService){
        this.eventService = eventService;
    }

    @PostMapping(value = "/event/post", consumes = {"*/*"})
    public ResponseEntity<String> createEvent(@RequestPart @Valid CreateEventResource eventResource,
                                              @RequestHeader(value = "Accept-Language", required = false) Locale locale,
                                              @RequestPart(value = "banner") MultipartFile banner,
                                              @RequestPart(value = "thumb") MultipartFile thumb) throws IOException, FileUploadException {
        return new ResponseEntity<>(eventService.createEvent(eventResource, locale, banner, thumb), HttpStatus.CREATED);
    }

    /*@GetMapping("/events")*/


    @GetMapping("/events")
    public ResponseEntity<List<EventDTO>> getAllEvents(@AuthenticationPrincipal Jwt principal) {
        return new ResponseEntity<>(eventService.getAllEventsFromTicketOffice(principal.getClaims().get("user_name").toString()), HttpStatus.OK);
    }


    @DeleteMapping("/event/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable("id") Long id){
        return new ResponseEntity<>(eventService.deleteEvent(id), HttpStatus.OK);
    }
}
