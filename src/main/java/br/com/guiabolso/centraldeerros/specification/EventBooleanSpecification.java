package br.com.guiabolso.centraldeerros.specification;

import br.com.guiabolso.centraldeerros.entity.Event;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class EventBooleanSpecification implements Specification<Event> {
	
	private static final long serialVersionUID = 2520940611645698063L;
	private String columnName;
	private Boolean columnValue;
	
	public EventBooleanSpecification(String name, Boolean value) {
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

