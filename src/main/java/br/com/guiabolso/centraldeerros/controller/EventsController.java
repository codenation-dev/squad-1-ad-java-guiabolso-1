package br.com.guiabolso.centraldeerros.controller;

import br.com.guiabolso.centraldeerros.dto.EventDTO;
import br.com.guiabolso.centraldeerros.entity.Event;
import br.com.guiabolso.centraldeerros.mapper.EventMapper;
import br.com.guiabolso.centraldeerros.service.EventService;
import br.com.guiabolso.centraldeerros.specification.EventBooleanSpecification;
import br.com.guiabolso.centraldeerros.specification.EventEnumSpecification;
import br.com.guiabolso.centraldeerros.specification.EventStringSpecification;
import lombok.AllArgsConstructor;
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

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.status;

@Controller
@AllArgsConstructor
@RequestMapping("/api/event")
public class EventsController {

	@Autowired
	EventService eventService;
	private EventMapper eventMapper;

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
	public ResponseEntity<EventDTO> create(@Valid @RequestBody Event event) {
		try {
			this.eventService.save(event);
			return status(HttpStatus.CREATED).body(eventMapper.map(event));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PatchMapping("{id}")
	public ResponseEntity<EventDTO> updateEvent(@Valid @RequestBody EventDTO eventDTO, @PathVariable Long id){
		try {
			Event eventUpdated = eventService.updateEvent(eventDTO, id);
			return status(HttpStatus.CREATED).body(eventMapper.map(eventUpdated));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


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