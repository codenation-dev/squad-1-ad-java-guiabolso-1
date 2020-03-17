package br.com.guiabolso.centraldeerros.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.guiabolso.centraldeerros.entity.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

}
