package org.terror.calendarapphard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.terror.calendarapphard.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
