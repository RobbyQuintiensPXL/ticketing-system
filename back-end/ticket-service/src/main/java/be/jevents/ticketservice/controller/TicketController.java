package be.jevents.ticketservice.controller;

import be.jevents.ticketservice.model.Event;
import be.jevents.ticketservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/{eventId}")
    public ResponseEntity<Event> getEventInfo(@PathVariable("eventId") Long id){
        return new ResponseEntity<>(ticketService.getEventInfo(id), HttpStatus.OK);
    }




}
