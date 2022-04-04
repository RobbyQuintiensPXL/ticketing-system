package be.jevent.eventservice.controller;

import be.jevent.eventservice.dto.TicketOfficeDTO;
import be.jevent.eventservice.service.TicketOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "ticketoffices")
public class TicketOfficeController {

    @Autowired
    private TicketOfficeService ticketOfficeService;

    @GetMapping
    public ResponseEntity<List<TicketOfficeDTO>> getAllTicketOffices(){
        return new ResponseEntity<>(ticketOfficeService.getAllTicketOffices(), HttpStatus.OK);
    }
}
