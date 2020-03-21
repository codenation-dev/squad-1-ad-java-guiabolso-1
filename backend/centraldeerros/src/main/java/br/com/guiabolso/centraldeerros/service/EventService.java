package br.com.guiabolso.centraldeerros.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import br.com.guiabolso.centraldeerros.entity.Event;
import br.com.guiabolso.centraldeerros.repositories.EventRepository;

@Service
public class EventService {
	@Autowired
	EventRepository eventRepository;
	public Page<Event> findAll(Specification<Event> spec, Pageable pageable){
		return eventRepository.findAll(spec, pageable);
	}

}
