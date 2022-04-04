package be.jevent.eventservice.repository;

import be.jevent.eventservice.model.Event;
import be.jevent.eventservice.model.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByEventName(String name);
    List<Event> findAllByEventName_AndLocation_City(String name, String city);
    List<Event> findAllByEventType(EventType type);
    List<Event> findAllByEventType_AndLocation_City(EventType type, String city);

}
