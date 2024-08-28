package org.terror.calendarapphard.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
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
        // 이 요청을 제외하곤 전부 인가 필터 작동시키기
        if (req.getRequestURI().startsWith("/api/members/signUp") || req.getRequestURI().startsWith("/api/members/signIn") ) {
            fc.doFilter(servletRequest, servletResponse);
        // 여기 요청들은 전부 인가 필터 작동시킴
        } else {
            // jwt 가 없는데 jwt 가 유효하지않다는 exception 나옴
            // 내일확인해보기
            String jwt = jm.getJwtFromHeader(req);
            boolean validJwt = jm.validateJwt(jwt);
            fc.doFilter(servletRequest, servletResponse);
        }
    }
}
