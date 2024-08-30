package org.terror.calendarapphard.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.terror.calendarapphard.entity.Calendar;
import org.terror.calendarapphard.entity.Comment;
import org.terror.calendarapphard.entity.Member;
import org.terror.calendarapphard.entity.Todo;
import org.terror.calendarapphard.enums.BaseResponseEnum;
import org.terror.calendarapphard.exceptions.BaseHandleException;
import org.terror.calendarapphard.exceptions.HandleDuplicateException;
import org.terror.calendarapphard.exceptions.HandleNotFoundException;
import org.terror.calendarapphard.repository.CalendarRepository;
import org.terror.calendarapphard.repository.CommentRepository;
import org.terror.calendarapphard.repository.MemberRepository;
import org.terror.calendarapphard.repository.TodoRepository;

import java.util.List;

/**
 * 조회 관련된 유틸 메서드 모음
 */
@RequiredArgsConstructor
@Component
public class UtilFind {
    private final MemberRepository memberRepository;
    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;
    private final CalendarRepository calendarRepository;


    //Member Email 로 찾기 (중복 확인용)
    public void duplicatedEmail(String email){
        // 여기서 true 가 나오면 중복됐다는 뜻임
        boolean isDuplicated = memberRepository.findByEmail(email).isPresent();
        // 중복 됐다면
        if (isDuplicated) throw new HandleDuplicateException(BaseResponseEnum.MEMBER_DUPLICATED);
    }

    /**
     * 유저 조회 메서드
     *
     * @param id / 조회할 유저 ID
     * @return Member / 조회된 유저 반환
     * @throws HandleNotFoundException / 조회에 실패하면 예외 처리 발생
     *
     * 조회에 성공하면, 유저를 반환합니다
     */
    public Member memberFindById(Long id){
        return memberRepository.findById(id).orElseThrow(() -> new HandleNotFoundException(BaseResponseEnum.MEMBER_NOT_FOUND));
    }

    /**
     * 유저 조회 메서드
     *
     * @param email / 조회할 유저 Email
     * @return Member / 조회된 유저 반환
     * @throws HandleNotFoundException / 조회에 실패하면 예외 처리 발생
     *
     * 조회에 성공하면, 유저를 반환합니다
     */
    public Member memberFindByEmail(String email){
        return memberRepository.findByEmail(email).orElseThrow(() -> new HandleNotFoundException(BaseResponseEnum.MEMBER_NOT_FOUND));
    }

    /**
     * 일정 조회 메서드
     *
     * @param id / 조회할 일정 ID
     * @return Todo / 조회된 일정 반환
     * @throws HandleNotFoundException / 조회에 실패하면 예외 처리 발생
     *
     * 조회에 성공하면, 유저를 반환합니다
     */
    public Todo todoFindById(Long id){
        return todoRepository.findById(id).orElseThrow(() -> new BaseHandleException(BaseResponseEnum.TODO_NOT_FOUND));
    }

    /**
     * 댓글 조회 메서드
     *
     * @param id / 조회할 댓글 ID
     * @return Comment / 조회된 댓글 반환
     * @throws HandleNotFoundException / 조회에 실패하면 예외 처리 발생
     *
     * 조회에 성공하면, 댓글을 반환합니다
     */
    public Comment commentFindById(Long id){
        return commentRepository.findById(id).orElseThrow(() -> new HandleNotFoundException(BaseResponseEnum.COMMENT_NOT_FOUND));
    }

    /**
     * 댓글 조회 메서드
     *
     * @return List<Comment> / 조회된 댓글들 반환
     *
     * 조회에 성공하면, 댓글들을 반환합니다
     */
    public List<Comment> commentsFindAll() {
        return commentRepository.findAll();
    }

    /**
     * 캘린더 조회 메서드
     *
     * @param memberId / 조회할 유저 ID
     * @param todoId / 조회할 일정 ID
     * @return Calendar / 조회된 캘린더 반환
     * @throws HandleNotFoundException / 조회에 실패하면 예외 처리 발생
     *
     * 조회에 성공하면, 캘린더를 반환합니다
     */
    public Calendar calendarFindByMemberId_TodoId(Long memberId, Long todoId){
        return calendarRepository.findByMemberIdAndTodoId(memberId,todoId).orElseThrow(() -> new HandleNotFoundException(BaseResponseEnum.CALENDAR_NOT_FOUND));
    }
}
