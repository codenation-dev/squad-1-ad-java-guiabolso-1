package br.com.guiabolso.centraldeerros.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.guiabolso.centraldeerros.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
	Page<Event> findAll(Specification<Event> spec, Pageable pageable);
}
