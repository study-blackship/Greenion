package com.blackship.greenion.user.oauth2.token;

import com.blackship.greenion.util.DateProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Base64;

@Service
public class TokenServiceImpl implements TokenService {
    private final DateProvider dateProvider;
    private final TokenProvider tokenProvider;

    private String secretKey = "secretKey";

    @PostConstruct
    void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public TokenServiceImpl(DateProvider dateProvider, TokenProvider tokenProvider) {
        this.dateProvider = dateProvider;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void verifyToken(String token) {
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);

        if (claims.getBody().getExpiration()
                .before(dateProvider.now())) {
            throw new TokenExpiredException();
        }
    }

    @Override
    public GreenionToken generateToken(String uid) {
        return tokenProvider.createToken(
                uid,
                dateProvider.now(),
                secretKey);
    }

    @Override
    public String extractUid(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
