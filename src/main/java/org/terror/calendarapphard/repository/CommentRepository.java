package org.terror.calendarapphard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.terror.calendarapphard.entity.Comment;

import java.util.List;
import java.util.Optional;

/**
 * 댓글 DB와 상호작용하는 JPA
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 없을수도 있기 때문에 Optional
    Optional<Comment> findByTodoIdAndId(Long todoId, Long commentId);
    // 없으면 빈 리스트 반환하면 되니까 Optional 사용안함
    List<Comment> findByTodoId(Long todoId);


}
