package org.terror.calendarapphard.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.terror.calendarapphard.entity.Calendar;
import org.terror.calendarapphard.entity.Comment;
import org.terror.calendarapphard.entity.Member;
import org.terror.calendarapphard.entity.Todo;
import org.terror.calendarapphard.enums.BaseResponseEnum;
import org.terror.calendarapphard.exceptions.HandleDuplicateException;
import org.terror.calendarapphard.exceptions.HandleNotFoundException;
import org.terror.calendarapphard.repository.CalendarRepository;
import org.terror.calendarapphard.repository.CommentRepository;
import org.terror.calendarapphard.repository.MemberRepository;
import org.terror.calendarapphard.repository.TodoRepository;

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

    //Member 찾는 메서드
    public Member memberFindById(Long id){
        return memberRepository.findById(id).orElseThrow(() -> new HandleNotFoundException(BaseResponseEnum.MEMBER_NOT_FOUND));
    }

    // todoId로 todo 찾는 메서드
    public Todo todoFindById(Long id){
        return todoRepository.findById(id).orElseThrow(() -> new HandleNotFoundException(BaseResponseEnum.TODO_NOT_FOUND));
    }

    //todoId,commentId로 comment 찾는 메서드
    public Comment commentFindById(Long id){
        return commentRepository.findById(id).orElseThrow(() -> new HandleNotFoundException(BaseResponseEnum.COMMENT_NOT_FOUND));
    }

    // memberId,todoId로 캘린더 찾기
    public Calendar calendarFindByMemberId_TodoId(Long memberId, Long todoId){
        return calendarRepository.findByMemberIdAndTodoId(memberId,todoId).orElseThrow(() -> new HandleNotFoundException(BaseResponseEnum.CALENDAR_NOT_FOUND));
    }

}
