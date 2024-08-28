package org.terror.calendarapphard.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.terror.calendarapphard.entity.Calendar;
import org.terror.calendarapphard.entity.Member;
import org.terror.calendarapphard.enums.BaseResponseEnum;
import org.terror.calendarapphard.enums.UserRole;
import org.terror.calendarapphard.exceptions.HandleNotFoundException;
import org.terror.calendarapphard.model.BaseResponseDto;
import org.terror.calendarapphard.model.JwtDto;
import org.terror.calendarapphard.model.memberDto.RequestMemberDto;
import org.terror.calendarapphard.model.memberDto.ResponseMemberDto;
import org.terror.calendarapphard.model.memberDto.SignInDto;
import org.terror.calendarapphard.repository.MemberRepository;
import org.terror.calendarapphard.util.JwtManager;
import org.terror.calendarapphard.util.UtilFind;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final UtilFind utilFind;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtManager jm;
    @Value("${authority.admin-key}")
    private String adminKey;

    @Transactional
    public String signUp(RequestMemberDto reqDto) {
        // 이메일 중복 여부 체크 메서드
        utilFind.duplicatedEmail(reqDto.getEmail());
        Member member = new Member(reqDto);
        String hashedPassword = passwordEncoder.encode(reqDto.getPassword());
        member.setPassword(hashedPassword);
        // reqAdminKey (사용자로부터 입력이 있고) 그 키가 adminKey 와 동일하다면
        if (StringUtils.hasText(reqDto.getReqAdminKey()) && reqDto.getReqAdminKey().equals(adminKey)) member.setRole(UserRole.ADMIN.getRole());
        // User 라면
        else member.setRole(UserRole.USER.getRole());
        memberRepository.save(member);
        return jm.createJwt(new JwtDto(member));
    }


    @Transactional(readOnly = true)
    public BaseResponseDto signIn(SignInDto user, HttpServletResponse res) {
        Member member = memberRepository.findByEmail(user.getEmail()).orElseThrow( () -> new HandleNotFoundException(BaseResponseEnum.MEMBER_NOT_FOUND));
        boolean isMatched = passwordEncoder.matches(user.getPassword(), member.getPassword());
        if (isMatched) {
            String jwt = jm.createJwt(new JwtDto(member));
            jm.addJwtToHeader(jwt,res);
            return new BaseResponseDto(BaseResponseEnum.MEMBER_LOGIN_SUCCESS);
        } else {
            return new BaseResponseDto(BaseResponseEnum.MEMBER_INVALID_CREDENTIALS);
        }
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
        utilFind.memberFindById(workerId);
        // 일정이 있는지 확인
        utilFind.todoFindById(todoId);
        Calendar calendar = utilFind.calendarFindByMemberId_TodoId(memberId,todoId);
        calendar.setWorkerId(workerId);
        return new BaseResponseDto(BaseResponseEnum.WORKER_SET_SUCCESS);
    }


}
