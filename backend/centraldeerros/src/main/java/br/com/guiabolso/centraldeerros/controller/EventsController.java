package br.com.guiabolso.centraldeerros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import br.com.guiabolso.centraldeerros.entity.Event;
import br.com.guiabolso.centraldeerros.service.EventService;
import br.com.guiabolso.centraldeerros.specification.EventEnumSpecification;
import br.com.guiabolso.centraldeerros.specification.EventStringSpecification;

import java.util.Optional;

@Controller
@RequestMapping
public class EventsController {
	@Autowired
	EventService eventService;

	@GetMapping(value = "/event", produces = "application/json")
	public ResponseEntity<Page<Event>> getEvent(@RequestParam(value = "level", required = false) String level,
			@RequestParam(value = "environment", required = false) String environment,
			@RequestParam(value = "origin", required = false) String origin,
			@RequestParam(value = "description", required = false) String description,
			@PageableDefault(value = 100, page = 0, direction = Direction.ASC, sort = "id") Pageable pageable) {
		try {
			Specification<Event> specifications = Specification.where(new EventEnumSpecification("levelEnum", level))
					.and(new EventStringSpecification("environment", environment))
					.and(new EventStringSpecification("origin", origin))
					.and(new EventStringSpecification("description", description));
			return new ResponseEntity<Page<Event>>(eventService.findAll(specifications, pageable), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Event>> getEvent(@PathVariable(value = "id")long id){
		try {
			return new ResponseEntity<>((Optional<Event>) eventService.get(id), HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping()
	public ResponseEntity<Event> addEvent(@RequestBody Event event) {
		try {

			return new ResponseEntity<>((Event) eventService.add(event), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
