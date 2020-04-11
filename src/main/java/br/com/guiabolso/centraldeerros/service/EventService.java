package br.com.guiabolso.centraldeerros.service;

import br.com.guiabolso.centraldeerros.dto.EventDTO;
import br.com.guiabolso.centraldeerros.entity.Event;
import br.com.guiabolso.centraldeerros.mapper.EventMapper;
import br.com.guiabolso.centraldeerros.repositories.EventRepository;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class EventService {

	private EventMapper eventMapper;

	@Autowired
    EventRepository eventRepository;


	public Page<EventDTO> findAll(Specification<Event> spec, Pageable pageable){
		findAllEventstoPage();
		return eventRepository.findAll(spec, pageable);
	}

	private void findAllEventstoPage(){
		List<Event> events = eventRepository.findAll();
		eventMapper.toList(events);
	}
	public Event save(Event event) {
		return eventRepository.save(event);
	}

	public EventDTO findById(Long id) {
		return eventMapper.map(eventRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException(
				"Event not found.",Event.class.getName())));
	}

	public Event updateEvent (EventDTO eventDTO, Long id){
		Event event = eventRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException(
				"Event not found.",Event.class.getName()));
		return eventRepository.save(eventMapper.updateEvent(eventDTO, event));
	}

	public void deleteEvent(Long id){
		Event event = eventRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException(
				"Event not found.",Event.class.getName()));
		eventRepository.deleteById(event.getId());
	}
}
