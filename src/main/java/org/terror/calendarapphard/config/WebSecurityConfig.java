package org.terror.calendarapphard.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.terror.calendarapphard.security.WebContextFailHandlerFilter;

// 전반적인 보안 및 필터 관련된 설정들
@RequiredArgsConstructor
@Component
public class WebSecurityConfig {

    // Json 형태의 데이터를 HTTP 응답에 담기위해 사용되는 객체
    private final ObjectMapper objectMapper;

    /**
     * @return BCryptPasswordEncoder 객체, Bean 반환
     * 비밀번호를 암호화 하기 위해 생성
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @return FilterRegistrationBean<WebContextFailHandlerFilter> 객체, Bean 반환
     * Servlet 필터에서 발생되는 exception 을 잡기 위해 생성, 그에 따른 filter 의 순서 지정
     */
    @Bean
    public FilterRegistrationBean<WebContextFailHandlerFilter> webContextFailHandlerFilter() {
        FilterRegistrationBean<WebContextFailHandlerFilter> authFailHandlerFilterBean = new FilterRegistrationBean<>();
        authFailHandlerFilterBean.setFilter(new WebContextFailHandlerFilter(objectMapper));
        authFailHandlerFilterBean.setOrder(1);
        return authFailHandlerFilterBean;
    }
}
