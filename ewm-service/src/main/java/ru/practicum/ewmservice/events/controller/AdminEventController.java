package ru.practicum.ewmservice.events.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmservice.events.dto.UpdateEventAdminRequest;
import ru.practicum.ewmservice.events.dto.EventDtoFull;
import ru.practicum.ewmservice.events.model.State;
import ru.practicum.ewmservice.events.model.StateAction;
import ru.practicum.ewmservice.events.service.EventService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor
@Slf4j
public class AdminEventController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<Collection<EventDtoFull>> searchEvents(@RequestParam(required = false) List<Long> users,
                                                                 @RequestParam(required = false) List<State> states,
                                                                 @RequestParam(required = false) List<Long> categories,
                                                                 @RequestParam(required = false) String rangeStart,
                                                                 @RequestParam(required = false) String rangeEnd,
                                                                 @RequestParam(name = "from", defaultValue = "0") Integer from,
                                                                 @RequestParam(name = "size", defaultValue = "10") Integer size) {
        log.info("Запрос от администратора на поиск событий");

        Collection<EventDtoFull> events = eventService.searchEvents(users, states, categories, rangeStart, rangeEnd, from, size);
        log.info("Событий найдено: {}", events.size());

        return ResponseEntity.ok(events);
    }

    @PatchMapping("/{eventId}")
    public ResponseEntity<EventDtoFull> editEvent(@PathVariable Long eventId,
                                                  @RequestBody(required = false) UpdateEventAdminRequest event) {
        log.info("Запрос от администратора на изменение события");

        if (event == null) {
            event = new UpdateEventAdminRequest();
            event.setStateAction(StateAction.REJECT_EVENT);
        }

        EventDtoFull updatedEvents = eventService.editEvent(eventId, event);
        log.info("Событие изменено");

        return ResponseEntity.ok(updatedEvents);
    }
}
