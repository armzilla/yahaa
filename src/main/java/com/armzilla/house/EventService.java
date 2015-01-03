package com.armzilla.house;

/**
 * Created by arm on 1/1/15.
 */

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import com.armzilla.house.dao.Event;
import com.armzilla.house.dao.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/event")
public class EventService {
    private static final Logger logger = Logger.getLogger(EventService.class.getName());

    @Autowired
    private EventRepository repository;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody ResponseEntity<String> createEvent(@RequestBody Event event) {
        event.setId(UUID.randomUUID().toString());
        event.setTime(Calendar.getInstance().getTimeInMillis());
        repository.save(event);
        logger.info(event.toString());
        return new ResponseEntity<>("OK" , HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json", params = "search")
    public @ResponseBody ResponseEntity<List<Event>> searchByDeviceId(@RequestParam(value="search") String id){
        List<Event> events = repository.findByDeviceIdOrderByTimeDesc(id);
        if(events == null || events.isEmpty()){
            return new ResponseEntity<List<Event>>(new LinkedList<Event>(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(events, HttpStatus.OK);
    }



}
