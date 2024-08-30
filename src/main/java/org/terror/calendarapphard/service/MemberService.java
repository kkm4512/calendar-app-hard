package org.terror.calendarapphard.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.terror.calendarapphard.entity.Calendar;
import org.terror.calendarapphard.entity.Member;
import org.terror.calendarapphard.enums.BaseResponseEnum;
import org.terror.calendarapphard.enums.UserRole;
import org.terror.calendarapphard.exceptions.HandleDuplicateException;
import org.terror.calendarapphard.exceptions.HandleNotFoundException;
import org.terror.calendarapphard.model.BaseResponseDto;
import org.terror.calendarapphard.model.JwtDto;
import org.terror.calendarapphard.model.memberDto.RequestMemberDto;
import org.terror.calendarapphard.model.memberDto.ResponseMemberDto;
import org.terror.calendarapphard.model.memberDto.SignInDto;
import org.terror.calendarapphard.repository.CalendarRepository;
import org.terror.calendarapphard.repository.MemberRepository;
import org.terror.calendarapphard.util.JwtManager;
import org.terror.calendarapphard.util.UtilFind;
import org.terror.calendarapphard.util.UtilResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final UtilFind utilFind;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtManager jm;
    // 사용자가 관리자 권한인지 확인하기위한 key
    @Value("${authority.admin-key}")
    private String adminKey;
    private final CalendarRepository calendarRepository;


    /**
     * 유저 회원가입 비즈니스 로직
     * @param reqDto / 저장할 유저의 정보
     * @throws HandleDuplicateException / 유저의 이메일이 중복되면 발생
     * @return String / 저장에 성공하면 Jwt값을 반환
     *
     * 유저의 정보를 받아 저장한후, 성공하면 유저의 정보로 생성한 Jwt값을 반환합니다
     */
    @Transactional
    public String signUp(RequestMemberDto reqDto) {
        // 이메일 중복 여부 체크 메서드
        utilFind.duplicatedEmail(reqDto.getEmail());
        String hashedPassword = passwordEncoder.encode(reqDto.getPassword());
        String role;
        // 사용자의 입력이 서버에서 가지고있는 adminKey와 동일하다면 (관리자 라면)
        if (StringUtils.hasText(reqDto.getReqAdminKey()) && reqDto.getReqAdminKey().equals(adminKey)) {
            role = UserRole.ADMIN.getRole();
        // 그 외 일반 유저라면
        } else {
            role = UserRole.USER.getRole();
        }

        Member member = Member.builder()
                .author(reqDto.getAuthor())
                .email(reqDto.getEmail())
                .password(hashedPassword)
                .role(role)
                .build();
        memberRepository.save(member);
        // 저장된 멤버의 정보로 Jwt를 생성하여 반환
        return jm.createJwt(new JwtDto(member));
    }


    /**
     * 유저 로그인 비즈니스 로직
     *
     * @param user / 로그인할 유저의 정보
     * @throws HandleNotFoundException / 유저가 존재하지 않을시 발생
     * @param res / Jwt를 담을 response
     * @return ResponseEntity<BaseResponseDto> / 로그인에 성공하면, 성공 여부를 알리는 응답 반환
     *
     * 유저의 정보확인후, 일치하는 경우 응답헤더에 Jwt를 추가합니다
     * 로그인 성공시 성공 응답을, 실패시 실패 응답을 반환합니다
     */
    @Transactional(readOnly = true)
    public ResponseEntity<BaseResponseDto> signIn(SignInDto user, HttpServletResponse res) {
        Member member = utilFind.memberFindByEmail(user.getEmail());
        boolean isMatched = passwordEncoder.matches(user.getPassword(), member.getPassword());
        if (isMatched) {
            String jwt = jm.createJwt(new JwtDto(member));
            jm.addJwtToHeader(jwt, res);
            return UtilResponse.getResponseEntity(BaseResponseEnum.MEMBER_LOGIN_SUCCESS);
        } else {
            return UtilResponse.getResponseEntity(BaseResponseEnum.MEMBER_INVALID_CREDENTIALS);
        }
    }

    /**
     * 단건 유저 조회 비즈니스 로직
     *
     * @param id / 조회할 유저의 ID
     * @throws HandleNotFoundException / 유저가 존재하지 않을시 발생
     * @return ResponseMemberDto / 조회에 성공하면, 조회한 유저 반환
     *
     * 유저를 조회하고, 성공했을시 유저를 반환합니다
     */
    @Transactional(readOnly = true)
    public ResponseMemberDto getMember(Long id) {
        Member member = utilFind.memberFindById(id);
        return new ResponseMemberDto(member);
    }

    /**
     * 다건 유저 조회 비즈니스 로직
     *
     * @return List<ResponseMemberDto> / 유저가 있을경우 유저들을 반환, 없다면 빈리스트를 반환
     *
     * 유저들이 있다면 모든 유저를 반환하고, 없다면 빈리스트를 반환합니다
     */
    @Transactional(readOnly = true)
    public List<ResponseMemberDto> getAllMember() {
        List<Member> members = memberRepository.findAll();
        return members.stream().map(ResponseMemberDto::new).toList();
    }

    /**
     * 유저 삭제 비즈니스 로직
     *
     * @param id / 삭제할 유저의 ID
     * @throws HandleNotFoundException / 유저가 존재하지 않을시 발생
     * @return ResponseEntity<BaseResponseDto> / 삭제 성공을 알리는 응답 반환
     *
     * 특정 유저를 삭제하는데 성공하면, 삭제 성공을 알리는 응답을 반환합니다
     */
    @Transactional
    public ResponseEntity<BaseResponseDto> deleteMember(Long id) {
        Member member = utilFind.memberFindById(id);
        memberRepository.delete(member);
        return UtilResponse.getResponseEntity(BaseResponseEnum.MEMBER_DELETE_SUCCESS);
    }

    /**
     * 특정 유저(담당자) 일정 배치 비즈니스 로직
     *
     * @param memberId / 유저 ID
     * @param todoId / 일정 ID
     * @param workerId / 담당자 ID
     * @throws HandleNotFoundException / 유저,일정,담당자가 존재하지 않을시 발생
     * @return ResponseEntity<BaseResponseDto> / 담당자 배치 성공여부를 알리는 응답을 반환
     *
     * 일정에 특정 담당자를 배치합니다, 유저와 일정이 존재하지않으면 예외를 발생시킵니다
     * 배치에 성공하면, 배치 성공 여부를 알리는 응답을 반환합니다
     */
    @Transactional
    public ResponseEntity<BaseResponseDto> setWorker(Long memberId, Long todoId, Long workerId) {
        // 유저가 있는지 확인
        utilFind.memberFindById(memberId);
        // 일정이 있는지 확인
        utilFind.todoFindById(todoId);
        // 담당자가 있는지 확인
        utilFind.memberFindById(workerId);
        Calendar calendar = utilFind.calendarFindByMemberId_TodoId(memberId, todoId);
        Calendar updateCalendar = Calendar.builder()
                .member(calendar.getMember())
                .todo(calendar.getTodo())
                .workerId(workerId)
                .build();
        calendarRepository.save(updateCalendar);
        return UtilResponse.getResponseEntity(BaseResponseEnum.WORKER_SET_SUCCESS);
    }
}
