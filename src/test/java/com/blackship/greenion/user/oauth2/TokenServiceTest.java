package com.blackship.greenion.user.oauth2;

import com.blackship.greenion.util.DateProvider;
import com.blackship.greenion.util.MockDateProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TokenServiceTest {

    private TokenService tokenService;
    private MockDateProvider mockDateProvider;

    @BeforeEach
    void setUp() {
        mockDateProvider = new MockDateProvider();
        tokenService = new TokenServiceImpl(mockDateProvider);
    }

    @Test
    void verifyToken_throwsTokenExpiredException_whenTokenExpired() {
        String givenTokenSubject = "token";

        Claims claims = Jwts.claims().setSubject(givenTokenSubject);

        Date issuedAt = new Date(2021, 10, 24, 0, 0, 0);
        Date expirationAt = new Date(issuedAt.getTime() - 1);

        mockDateProvider.now_returnValue = issuedAt;

        String givenToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(issuedAt)
                .setExpiration(expirationAt)
                .signWith(SignatureAlgorithm.HS256, "secretKey")
                .compact();

        assertThrows(TokenExpiredException.class, () -> {
           tokenService.verifyToken(givenToken);
        });
    }

    @Test
    void name() {

    }
}