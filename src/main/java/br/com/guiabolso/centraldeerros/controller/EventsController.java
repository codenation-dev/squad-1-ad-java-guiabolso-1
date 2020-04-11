package br.com.guiabolso.centraldeerros.controller;

import br.com.guiabolso.centraldeerros.dto.EventDTO;
import br.com.guiabolso.centraldeerros.entity.Event;
import br.com.guiabolso.centraldeerros.mapper.EventMapper;
import br.com.guiabolso.centraldeerros.service.EventService;
import br.com.guiabolso.centraldeerros.specification.EventBooleanSpecification;
import br.com.guiabolso.centraldeerros.specification.EventEnumSpecification;
import br.com.guiabolso.centraldeerros.specification.EventStringSpecification;
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

import static org.springframework.http.ResponseEntity.status;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@Controller
@RequestMapping("api/event")
public class EventsController {
	@Autowired
	EventService eventService;
	EventMapper eventMapper;

	@GetMapping(produces = "application/json")
	public ResponseEntity<Page<EventDTO>> getEvent(@RequestParam(value = "level", required = false) String level,
												   @RequestParam(value = "environment", required = false) String environment,
												   @RequestParam(value = "origin", required = false) String origin,
												   @RequestParam(value = "description", required = false) String description,
												   @RequestParam(value = "archived", required = false) Boolean archived,
												   @PageableDefault(value = 100, page = 0, direction =
														   Direction.ASC,
														   sort = "id") Pageable pageable) {
		try {
			Specification<Event> specifications = Specification.where(new EventEnumSpecification("levelEnum", level))
					.and(new EventStringSpecification("environment", environment))
					.and(new EventStringSpecification("origin", origin))
					.and(new EventStringSpecification("description", description))
					.and(new EventBooleanSpecification("archived", archived));
			return new ResponseEntity<>((eventService.findAll(specifications, pageable)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<EventDTO> getEvent(@PathVariable(value = "id") Long id) {
		try {
			return new ResponseEntity<>(eventService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(produces = "application/json")
	public ResponseEntity<Event> create(@Valid @RequestBody Event event) {
		try {
			this.eventService.save(event);
			return status(HttpStatus.CREATED).body(event);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	@PutMapping(value = "/{id}", produces = "application/json")
//	public ResponseEntity<Event> updateEvent(@Valid @RequestBody Event event, @PathVariable(value = "id") long id) {
//		try {
//			Optional<Event> event1 = eventService.findById(id);
//			if (event1.isPresent()) {
//				event.setId(event1.get().getId());
//				return new ResponseEntity<>(eventService.update(event), HttpStatus.OK);
//			}
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}


	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteEvent(@PathVariable Long id) {
		try {
			this.eventService.deleteEvent(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}