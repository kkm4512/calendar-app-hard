package org.terror.calendarapphard.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;
import org.terror.calendarapphard.exceptions.BaseHandleException;
import org.terror.calendarapphard.model.BaseResponseDto;

import java.io.IOException;

/**
 * Servlet 필터에서 발생되는 예외 처리 클래스
 *
 * 서블릿 필터에서 잡히는 에러를 직접 필터를 만들어 커스텀하게 처리합니다
 * 만약 잡히지 않는다면 다음필터로 넘깁니다
 */
@Slf4j(topic = "Servlet Filter Exception Handling")
@RequiredArgsConstructor
public class WebContextFailHandlerFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
            // 모든 exception 을 하나로 상속받게하여 처리
        } catch (BaseHandleException e) {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(e.getBaseResponseEnum().getStatus());
            response.getWriter()
                    .write(objectMapper.writeValueAsString(new BaseResponseDto(e.getBaseResponseEnum())));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
