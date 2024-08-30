package org.terror.calendarapphard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.terror.calendarapphard.entity.Member;

import java.util.Optional;

/**
 * 유저 DB와 상호작용하는 JPA
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
