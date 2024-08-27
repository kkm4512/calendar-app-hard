package org.terror.calendarapphard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.terror.calendarapphard.entity.Calendar;

import java.util.Optional;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    //memberId,todoId로 캘린더 찾기

    Optional<Calendar> findByMemberIdAndTodoId(Long memberId, Long todoId);
}
