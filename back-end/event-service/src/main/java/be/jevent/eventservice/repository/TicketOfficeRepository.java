package be.jevent.eventservice.repository;

import be.jevent.eventservice.model.TicketOffice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketOfficeRepository extends JpaRepository<TicketOffice, Long> {

    Optional<TicketOffice> findByOrganisation(String organisation);
}
