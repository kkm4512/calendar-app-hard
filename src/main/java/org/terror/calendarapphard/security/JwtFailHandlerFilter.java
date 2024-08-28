package org.terror.calendarapphard.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import org.terror.calendarapphard.exceptions.HandleNotFoundException;
import org.terror.calendarapphard.exceptions.HandleUnVerifiedJwt;
import org.terror.calendarapphard.model.BaseResponseDto;

import java.io.IOException;

// AuthorizationFilter 에서 exception 처리 하여도 계속 500  에러만 나와
// Web Context 단에서 발생 하는 예외를 캐치하고 내보냄
public class JwtFailHandlerFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    public JwtFailHandlerFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        // Jwt 유효 하지 않을때 ( 시그니처 검증 실패, 올바른 형식 아님 등등 )
        } catch (HandleUnVerifiedJwt e) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter()
                    .write(objectMapper.writeValueAsString(new BaseResponseDto(e.getBaseResponseEnum())));
        // Jwt 찾을 수 없을때
        } catch (HandleNotFoundException e) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter()
                    .write(objectMapper.writeValueAsString(new BaseResponseDto(e.getBaseResponseEnum())));
        }
    }
}
