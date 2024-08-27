package org.terror.calendarapphard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.terror.calendarapphard.entity.Todo;

@Repository
public interface PageableRepository extends JpaRepository<Todo, Long> {
}
