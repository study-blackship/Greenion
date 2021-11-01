package com.blackship.greenion.user.oauth2.token;

import com.blackship.greenion.util.MockDateProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TokenServiceTest {

    private TokenService tokenService;
    private MockDateProvider mockDateProvider;
    private SpyTokenProvider spyTokenProvider;

    @BeforeEach
    void setUp() {
        mockDateProvider = new MockDateProvider();
        spyTokenProvider = new SpyTokenProvider();
        tokenService = new TokenServiceImpl(mockDateProvider, spyTokenProvider);
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

        assertThrows(TokenExpiredException.class, () -> tokenService.verifyToken(givenToken));
    }

    @Test
    void verifyToken_success() {
        String givenTokenSubject = "token";

        Claims claims = Jwts.claims().setSubject(givenTokenSubject);

        Date issuedAt = new Date(2021, 10, 24, 0, 0, 0);
        Date expirationAt = new Date(issuedAt.getTime() + 1);

        mockDateProvider.now_returnValue = issuedAt;

        String givenToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(issuedAt)
                .setExpiration(expirationAt)
                .signWith(SignatureAlgorithm.HS256, "secretKey")
                .compact();

        tokenService.verifyToken(givenToken);
    }

    @Test
    void generateToken_callsCreateToken_inTokenProvider() {
        String givenUid = "uid";
        Date givenDate = new Date();
        mockDateProvider.now_returnValue = givenDate;

        tokenService.generateToken(givenUid);

        assertThat(spyTokenProvider.createToken_argumentsUid).isEqualTo(givenUid);
        assertThat(spyTokenProvider.createToken_argumentDate).isEqualTo(givenDate);
    }

    @Test
    void generateToken_returnsGreenionToken() {
        GreenionToken givenGreenionToken = new GreenionToken("token");
        spyTokenProvider.createToken_returnValue = givenGreenionToken;

        GreenionToken actualGreenionToken = tokenService.generateToken("");

        assertThat(actualGreenionToken).isEqualTo(givenGreenionToken);
    }
}