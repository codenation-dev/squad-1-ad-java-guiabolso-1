package br.com.guiabolso.centraldeerros.service;

import br.com.guiabolso.centraldeerros.dto.EventDTO;
import br.com.guiabolso.centraldeerros.entity.Event;
import br.com.guiabolso.centraldeerros.mapper.EventMapper;
import br.com.guiabolso.centraldeerros.repositories.EventRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventService {
	@Autowired
    EventRepository eventRepository;


	public Page<Event> findAll(Specification<Event> spec, Pageable pageable) {
		return eventRepository.findAll(spec, pageable);
	}


	public Event save(Event event) {
		return eventRepository.save(event);
	}


	public EventDTO findById(Long id) {
		return EventMapper.toEventDTO(eventRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Evento não encontrado",Event.class.getName())));
	}

	public Event update(EventDTO eventDTO, Long id){
		Event event = EventMapper.toEvent(eventDTO, eventRepository.findById(id));
		BeanUtils.copyProperties(eventDTO, event, "id");
		return eventRepository.save(event);
	}

	public void deleteEvent(Long id){
		eventRepository.deleteById(id);
	}

}
