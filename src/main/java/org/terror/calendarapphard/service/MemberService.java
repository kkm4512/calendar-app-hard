package org.terror.calendarapphard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terror.calendarapphard.entity.Calendar;
import org.terror.calendarapphard.entity.Member;
import org.terror.calendarapphard.entity.Todo;
import org.terror.calendarapphard.enums.BaseResponseEnum;
import org.terror.calendarapphard.model.BaseResponseDto;
import org.terror.calendarapphard.model.JwtDto;
import org.terror.calendarapphard.model.memberDto.RequestMemberDto;
import org.terror.calendarapphard.model.memberDto.ResponseMemberDto;
import org.terror.calendarapphard.repository.MemberRepository;
import org.terror.calendarapphard.util.JwtManager;
import org.terror.calendarapphard.util.UtilFind;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final UtilFind utilFind;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtManager jm;

    @Transactional
    public String createMember(RequestMemberDto reqDto) {
        Member member = new Member(reqDto);
        String hashedPassword = passwordEncoder.encode(reqDto.getPassword());
        member.setPassword(hashedPassword);
        String jwt = jm.createJwt(new JwtDto(member));
        memberRepository.save(member);
        return jwt;
    }

    @Transactional(readOnly = true)
    public ResponseMemberDto getMember(Long id) {
        Member member = utilFind.memberFindById(id);
        return new ResponseMemberDto(member);
    }

    @Transactional(readOnly = true)
    public List<ResponseMemberDto> getAllMember() {
        List<Member> members = memberRepository.findAll();
        return members.stream().map(ResponseMemberDto::new).toList();
    }

    @Transactional
    public BaseResponseDto deleteMember(Long id) {
        Member member = utilFind.memberFindById(id);
        memberRepository.delete(member);
        return new BaseResponseDto(BaseResponseEnum.MEMBER_DELETE_SUCCESS);
    }

    @Transactional
    public BaseResponseDto setWorker(Long memberId,Long todoId, Long workerId) {
        // 담당자가 있는지 확인
        Member member = utilFind.memberFindById(workerId);
        // 일정이 있는지 확인
        Todo todo = utilFind.todoFindById(todoId);
        Calendar calendar = utilFind.calendarFindByMemberId_TodoId(memberId,todoId);
        calendar.setWorkerId(workerId);
        return new BaseResponseDto(BaseResponseEnum.WORKER_SET_SUCCESS);
    }
}
