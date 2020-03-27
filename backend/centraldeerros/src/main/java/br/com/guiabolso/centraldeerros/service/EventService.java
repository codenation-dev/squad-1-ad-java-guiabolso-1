package br.com.guiabolso.centraldeerros.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.guiabolso.centraldeerros.entity.Event;
import br.com.guiabolso.centraldeerros.repositories.EventRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.geom.RectangularShape;
import java.util.Optional;

@Service
public class EventService {
	@Autowired
	EventRepository eventRepository;
	public Page<Event> findAll(Specification<Event> spec, Pageable pageable){
		return eventRepository.findAll(spec, pageable);
	}
	public Event add(Event object) {
		return eventRepository.save((Event) object);
	}


	public Optional<Event> get(Long id) {
		return eventRepository.findById(id);
	}

	public Event update(Event object){
		return eventRepository.save((Event) object);
	}



}
