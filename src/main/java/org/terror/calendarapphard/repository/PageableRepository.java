package org.terror.calendarapphard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.terror.calendarapphard.entity.Todo;

public interface PageableRepository extends JpaRepository<Todo, Long> {
}
