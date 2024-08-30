package org.terror.calendarapphard.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.terror.calendarapphard.entity.Member;
import org.terror.calendarapphard.enums.BaseResponseEnum;
import org.terror.calendarapphard.enums.UserRole;
import org.terror.calendarapphard.exceptions.HandleAuthorityException;
import org.terror.calendarapphard.util.JwtManager;
import org.terror.calendarapphard.util.UtilFind;

import java.io.IOException;

/**
 * 클라이언트의 요청중 인증,인가 처리 담당 필터
 *
 * 회원가입,로그인 URL접속시 인증,인가 패스
 * 일정 수정,삭제 URL접속시 관리자 권한인지 확인 관리자라면 다음 필터로 넘깁니다
 * 그 외 URL로 접속했을시 전부 인증,인가 처리를 합니다
 * @throws HandleAuthorityException / 관리자 권한이 없다면 예외를 발생시킵니다
 */
@Slf4j(topic = "AuthorizationFilter")
@Component
@Order(1)
@RequiredArgsConstructor
public class AuthorizationFilter implements Filter {
    private final JwtManager jm;
    private final UtilFind utilFind;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String reqURI = req.getRequestURI();
        String reqMethod = req.getMethod();
        // 이 요청을 제외하곤 전부 인가 필터 작동시키기
        if (reqURI.startsWith("/api/members/signUp") || reqURI.startsWith("/api/members/signIn") ) {
            fc.doFilter(servletRequest, servletResponse);
        // todo의 일정 수정 및 삭제 요청 들어올때
        } else if (reqURI.startsWith("/api/todos/") && "PUT".equals(reqMethod) || reqURI.startsWith("/api/todos/") && "DELETE".equals(reqMethod)) {
            String jwt = jm.getJwtFromHeader(req);
            Claims claims = jm.getUserInfoFromJwt(jwt);
            Member member = utilFind.memberFindById(Long.parseLong(claims.getSubject()));
            // 관리자 라면
            if (member.getRole().equals(UserRole.Authority.ADMIN)) fc.doFilter(servletRequest, servletResponse);
            else throw new HandleAuthorityException(BaseResponseEnum.NOT_ADMIN);
        // 여기 요청들은 전부 인가 필터 작동시킴
        } else {
            String jwt = jm.getJwtFromHeader(req);
            jm.validateJwt(jwt);
            fc.doFilter(servletRequest, servletResponse);
        }
    }
}
