package br.com.guiabolso.centraldeerros.service;

import br.com.guiabolso.centraldeerros.dto.EventDTO;
import br.com.guiabolso.centraldeerros.entity.Event;
import br.com.guiabolso.centraldeerros.mapper.EventMapper;
import br.com.guiabolso.centraldeerros.repositories.EventRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

	@Autowired
    EventRepository eventRepository;

	public Page<EventDTO> findAll(Specification<Event> spec, Pageable pageable){
		findAllEventstoPage();
		return eventRepository.findAll(spec, pageable);
	}

	private void findAllEventstoPage(){
		List<Event> events = eventRepository.findAll();
		EventMapper.toPageDTO(events);
	}
	public Event save(Event event) {
		return eventRepository.save(event);
	}

	public Optional<Event> findById(Long id) {
		return eventRepository.findById(id);
	}

	public Event update(Event event){
		return eventRepository.save(event);
	}

	public void deleteEvent(Long id){
		Event event = eventRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException(
				"Event not found.",Event.class.getName()));
		eventRepository.deleteById(event.getId());
	}
}
