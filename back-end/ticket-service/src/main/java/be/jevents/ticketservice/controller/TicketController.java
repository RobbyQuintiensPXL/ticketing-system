package be.jevents.ticketservice.controller;

import be.jevents.ticketservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;




}
