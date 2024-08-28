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
