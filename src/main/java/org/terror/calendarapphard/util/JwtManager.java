package org.terror.calendarapphard.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.terror.calendarapphard.model.JwtDto;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j(topic = "JwtManger")
@Component
public class JwtManager {

    // HeaderKey 값
    public static final String AUTHORIZATION_HEADER = "Authorization";
    // Token 식별자
    public static final String BEARER = "Bearer ";
    // 사용자 권한 값의 key
    public static final String AUTHORIZATION_KEY = "auth";
    // 토큰 만료시간 (60 * 60 * 1000 = 360000ms ->  3600초 -> 60분 -> 1시간)
    public static final Long EXPIRES_TOKEN = 60 * 60 * 1000L;
    // jwt secretKey
    @Value("${jwt.secret-key}")
    private String JWT_SECRET_KEY;
    // 사용할 알고리즘
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    // JWT 를생성,검증할때 사용되는 암호화 키
    private static Key key;

    //PostConstruct 로인해, JwtUtil 의 모든 인스턴스가 생성되고, 모든 의존성이 주입된 맨 마지막에 호출됨
    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(JWT_SECRET_KEY);
        key = Keys.hmacShaKeyFor(bytes);
    }

    //Jwt 생성
    public String createJwt(JwtDto jwtDto) {
        log.info("JWT 생성을 시작 합니다");
        Date date = new Date();
        String jwt =  BEARER +
                Jwts.builder()
                        .claim(jwtDto.getAuthor(),"author")
                        .claim(jwtDto.getEmail(),"email")
                        .setSubject(String.valueOf(jwtDto.getId())) //사용자 식별값 (ID)
                        .setExpiration(new Date(date.getTime() + EXPIRES_TOKEN)) // 만료 시간
                        .setIssuedAt(date) // 발급일
                        .signWith(key,signatureAlgorithm) // 암호화 알고리즘
                        .compact(); // 최종적인 jwt 생성
        log.info("JWT 생성이 완료 되었습니다 생성된 Jwt 값 : {}",jwt);
        return jwt;
    }
}
