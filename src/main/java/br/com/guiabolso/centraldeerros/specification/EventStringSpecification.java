package br.com.guiabolso.centraldeerros.specification;

import br.com.guiabolso.centraldeerros.entity.Event;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class EventStringSpecification implements Specification<Event> {

	private static final long serialVersionUID = -6140750160778430686L;
	private String columnName;
	private String columnValue;
	
	public EventStringSpecification(String name, String value) {
		this.columnName = name;
		this.columnValue = value;
	}

	@Override
	public Predicate toPredicate(Root<Event> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (this.columnValue == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        return criteriaBuilder.equal(root.get(this.columnName), this.columnValue);
	}

}

