package br.com.guiabolso.centraldeerros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.guiabolso.centraldeerros.entity.Event;
import br.com.guiabolso.centraldeerros.repositories.EventRepository;

@Service
public class EventService {
	
	@Autowired(required=false)
	private EventRepository eventRepository;
	
	public EventService(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}
	
	public Iterable<Event> list() {
		return eventRepository.findAll();
	}
	
	public Iterable<Event> save(List<Event> events) {
		return eventRepository.saveAll(events);
	}
	
}
