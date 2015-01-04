package com.armzilla.house;

/**
 * Created by arm on 1/1/15.
 */

import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import com.armzilla.house.dao.Event;
import com.armzilla.house.dao.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
@RequestMapping("/api/event")
public class EventService {
    private static final Logger logger = Logger.getLogger(EventService.class.getName());

    @Autowired
    private EventRepository repository;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
    public ResponseEntity<String> createEvent(@RequestBody Event event) {
        event.setId(UUID.randomUUID().toString());
        event.setTime(Calendar.getInstance().getTimeInMillis());
        Event result = repository.save(event);
        logger.info(event.toString());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri());
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", params = "device_id")
    public ResponseEntity<List<Event>> searchByDeviceId(@RequestParam(value="search") String id){
        List<Event> events = repository.findByDeviceIdOrderByTimeDesc(id);
        if(events == null || events.isEmpty()) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(events, null, HttpStatus.OK);
    }
}
