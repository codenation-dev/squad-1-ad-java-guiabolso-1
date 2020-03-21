package br.com.guiabolso.centraldeerros.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.guiabolso.centraldeerros.entity.Event;

public interface EventRepository extends CrudRepository<Event, Long> {
	
	   List <Event> findAll();

	   List <Event> findAllOrderByLevel();

	   List <Event> findAllOrderByEventsQuantity();
}

