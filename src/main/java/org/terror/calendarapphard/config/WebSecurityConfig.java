package org.terror.calendarapphard.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.terror.calendarapphard.security.WebContextFailHandlerFilter;

@RequiredArgsConstructor
@Component
// Filter 단에서 생기는 exception 직접 커스텀하여 핸들링하기 위해 생성
public class WebSecurityConfig {
    private final ObjectMapper objectMapper;
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public FilterRegistrationBean<WebContextFailHandlerFilter> webContextFailHandlerFilter() {
        FilterRegistrationBean<WebContextFailHandlerFilter> authFailHandlerFilterBean = new FilterRegistrationBean<>();
        authFailHandlerFilterBean.setFilter(new WebContextFailHandlerFilter(objectMapper));
        authFailHandlerFilterBean.setOrder(1);
        return authFailHandlerFilterBean;
    }
}
