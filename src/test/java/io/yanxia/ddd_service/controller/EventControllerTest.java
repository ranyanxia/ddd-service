package io.yanxia.ddd_service.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.*;

import io.yanxia.ddd_service.model.Event;
import io.yanxia.ddd_service.repository.EventRepository;
import io.yanxia.ddd_service.service.EventService;

@SpringBootTest
// @WebMvcTest(EventController.class)
public class EventControllerTest {
    @Autowired
    private EventService eventService;
    // @Autowired
    // private MockMvc mockMvc;

    @MockBean(EventRepository.class)
    private EventRepository eventRepository;

    @Test
    void whenGetAllEvents_thenSuccess() {
        Event event = new Event("1", "Event 1", "this is event 01 body");
        when(eventRepository.findAll()).thenReturn(Arrays.asList(event));

        List<Event> events = eventService.getAllEvents();

        assertNotNull(events);
        assertEquals(1, events.size());
    }

    @Test
    void testDeleteEvent() {

    }

    // @Test
    // void testGetAllEvents() throws Exception {
    //     Event event = new Event("1", "Event 1", "this is event 01 body");
    //     when(eventRepository.findAll()).thenReturn(Arrays.asList(event));

    //     var eventsStr = event.toString();

    //     mockMvc.perform(get("/events"))
    //     .andExpect(status().isOk())
    //     .andExpect(content().string(eventsStr));
    // }

    @Test
    void testGetEventById() {

    }

    @Test
    void testUpdateEvent() {

    }
}
