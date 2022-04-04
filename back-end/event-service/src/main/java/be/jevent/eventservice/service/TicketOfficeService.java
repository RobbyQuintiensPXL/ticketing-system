package be.jevent.eventservice.service;

import be.jevent.eventservice.dto.TicketOfficeDTO;
import be.jevent.eventservice.exception.TicketOfficeException;
import be.jevent.eventservice.repository.TicketOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketOfficeService {

    @Autowired
    private TicketOfficeRepository ticketOfficeRepository;

    @Autowired
    private MessageSource messageSource;

    public List<TicketOfficeDTO> getAllTicketOffices() {
        List<TicketOfficeDTO> ticketOfficeDTOList = ticketOfficeRepository.findAll().stream().map(TicketOfficeDTO::new).collect(Collectors.toList());
        if (ticketOfficeDTOList.isEmpty()){
            throw new TicketOfficeException("No ticket offices found");
        }
        return ticketOfficeDTOList;
    }


}
