package io.yanxia.ddd_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import io.yanxia.ddd_service.model.Event;
import io.yanxia.ddd_service.repository.EventRepository;

@Service
public class EventService {
    
    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(String id) {
        return eventRepository.findById(id).orElse(null);
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(String id, Event eventUpdate) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event != null) {
            event.setName(eventUpdate.getName());
            event.setPayload(eventUpdate.getPayload());
            eventRepository.save(event);
        }
        return null;
    }

    public void deleteEvent(String id) {
        eventRepository.deleteById(id);
    }

}
