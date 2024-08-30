package org.terror.calendarapphard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.terror.calendarapphard.entity.Todo;

import java.util.Optional;

/**
 * 일정 DB와 상호작용하는 JPA
 */
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    //memberId,todoId로 일정 찾기
    Optional<Todo> findByMemberIdAndId(Long memberId, Long todoId);
}
