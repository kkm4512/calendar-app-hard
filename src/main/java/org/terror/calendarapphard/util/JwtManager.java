package org.terror.calendarapphard.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.terror.calendarapphard.enums.BaseResponseEnum;
import org.terror.calendarapphard.exceptions.HandleNotFoundException;
import org.terror.calendarapphard.exceptions.HandleUnSupportEncoding;
import org.terror.calendarapphard.exceptions.HandleUnVerifiedJwt;
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
        log.info("JWT 생성 시작");
        Date date = new Date();
        String jwt = BEARER +
                Jwts.builder()
                        .claim("author", jwtDto.getAuthor())
                        .claim("email", jwtDto.getEmail())
                        .setSubject(String.valueOf(jwtDto.getId())) //사용자 식별값 (ID)
                        .setExpiration(new Date(date.getTime() + EXPIRES_TOKEN)) // 만료 시간
                        .setIssuedAt(date) // 발급일
                        .signWith(key, signatureAlgorithm) // 암호화 알고리즘
                        .compact(); // 최종적인 jwt 생성
        log.info("JWT 생성 종료, 생성된 Jwt 값 : {}", jwt);
        return jwt;
    }

    // Jwt Header 에 저장
    public void addJwtToHeader(String jwt, HttpServletResponse res) {
        log.info("Jwt 헤더에 넣기 시작");
        try {
            res.addHeader(AUTHORIZATION_KEY, jwt); // 응답 객체에 우리가 만든 Name-Value 쿠키값 추가
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new HandleUnSupportEncoding(BaseResponseEnum.UNSUPPORTED_ENCODING);
        }
        log.info("JWT 헤더에 넣기 종료");
    }

    // Jwt 토큰 잘라주기
    public String substringJwt(String jwt) {
        log.info("Jwt 자르기 시작");
        String subJwt = jwt.substring(7);
        log.info("Jwt 자르기 종료");
        return subJwt;
    }

    // jwt 검증
    public boolean validateJwt(String jwt) {
        try {
            log.info("Jwt 유효 검증 시작");
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
        } catch (ExpiredJwtException e) {
            log.error(e.getMessage());
            throw new HandleUnVerifiedJwt(BaseResponseEnum.JWT_EXPIRED);
        } catch (UnsupportedJwtException e) {
            log.error(e.getMessage());
            throw new HandleUnVerifiedJwt(BaseResponseEnum.JWT_UNSUPPORTED);
        } catch (MalformedJwtException e) {
            log.error(e.getMessage());
            throw new HandleUnVerifiedJwt(BaseResponseEnum.JWT_MALFORMED);
        } catch (SignatureException e) {
            log.error(e.getMessage());
            throw new HandleUnVerifiedJwt(BaseResponseEnum.JWT_SIGNATURE_FAIL);
        }
        log.info("Jwt 유효 검증 종료");
        return true;
    }

    // Jwt 에서 사용자 정보 추출
    public Claims getUserInfoFromJwt(String jwt) {
        log.info("사용자 추출 시작");
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        log.info("사용자 추출 종료");
        return claims;
    }

    // Header 에서 Jwt 뽑아오기
    public String getJwtFromHeader(HttpServletRequest request) {
        log.info("헤더에서 Jwt 뽑기 시작");
        String jwt = request.getHeader(AUTHORIZATION_HEADER);
        // 접두어가 "Bearer " 체크 하는 로직 if문 내에서 제외, Jwt 형식이 유효하지않다면 validate 에서 exception 처리 하고 있기 때문에
        if (StringUtils.hasText(jwt)) {
            log.info("헤더에서 Jwt 뽑기 종료, 추출한 jwt : {} ", jwt);
            return jwt.substring(7);
        } else {
            throw new HandleNotFoundException(BaseResponseEnum.JWT_NOT_FOUND);
        }
    }
}
