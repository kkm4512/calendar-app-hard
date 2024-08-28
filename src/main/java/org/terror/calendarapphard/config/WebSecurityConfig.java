package org.terror.calendarapphard.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.terror.calendarapphard.security.JwtFailHandlerFilter;

@RequiredArgsConstructor
@Component
public class WebSecurityConfig {
    private final ObjectMapper objectMapper;
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public FilterRegistrationBean<JwtFailHandlerFilter> AuthFailHandlerFilter() {
        FilterRegistrationBean<JwtFailHandlerFilter> authFailHandlerFilterBean = new FilterRegistrationBean<>();
        authFailHandlerFilterBean.setFilter(new JwtFailHandlerFilter(objectMapper));
        authFailHandlerFilterBean.setOrder(1);
        return authFailHandlerFilterBean;
    }
}
