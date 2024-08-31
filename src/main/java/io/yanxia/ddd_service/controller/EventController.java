package io.yanxia.ddd_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.yanxia.ddd_service.model.Event;
import io.yanxia.ddd_service.service.EventService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/events")
@Api(tags = "Events API")
public class EventController {
    @Autowired
    private EventService eventService;

    @ApiOperation(value = "Get All Events", notes = "Return a list of all events")
    @GetMapping
    public List<Event> getAllEvents() {
        log.info("get all events");
        return eventService.getAllEvents();
    }

    @ApiOperation(value = "Get Event by ID", notes = "Return an event by given ID")
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable String id) {
        log.info("query event by id: {}", id);
        Event event = eventService.getEventById(id);
        if (event != null) {
            return ResponseEntity.ok(event);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        log.info("create new Event : {}", event.getId());
        return eventService.createEvent(event);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable String id, @RequestBody Event updEvent) {
        log.info("update event : {}", id);
        Event event = eventService.updateEvent(id, updEvent);
        return (event != null) ? ResponseEntity.ok(event) : ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String id) {
        log.info("delete event by id : {}", id);
        eventService.deleteEvent(id);

        return ResponseEntity.ok().build();
    }
}
