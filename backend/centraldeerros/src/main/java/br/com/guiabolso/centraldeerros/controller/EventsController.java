package br.com.guiabolso.centraldeerros.controller;

import br.com.guiabolso.centraldeerros.entity.Event;
import br.com.guiabolso.centraldeerros.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
public class EventsController {

    @Autowired
    private EventService eventService;


    @GetMapping("/event/{id}")
    public ResponseEntity<Optional<Event>> getEvent(@PathVariable(value = "id")long id){
         try {
             return new ResponseEntity<>((Optional<Event>) eventService.get(id), HttpStatus.OK);
         }catch (Exception e) {
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }
    }

    @PostMapping("/event")
    public ResponseEntity<Event> addEvent(@RequestBody Event event) {
        try {

            return new ResponseEntity<>((Event) eventService.add(event), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
