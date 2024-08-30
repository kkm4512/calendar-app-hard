package org.terror.calendarapphard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.terror.calendarapphard.entity.Todo;

/**
 * 일정 페이지와 상호작용하는 JPA
 */
@Repository
public interface PageableRepository extends JpaRepository<Todo, Long> {
}
