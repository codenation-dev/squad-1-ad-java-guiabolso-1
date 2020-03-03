package br.com.guiabolso.centraldeerros.service;

import org.springframework.beans.factory.annotation.Autowired;
import br.com.guiabolso.centraldeerros.repositories.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventService eventRepository;

    public Event add(Event object) {

        return eventRepository.save(Event) object);

    public Event get(Event id) {

        return eventRepository.findByID(id);
        }

    }
}
