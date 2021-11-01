package com.blackship.greenion.user.oauth2.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenProviderImpl implements TokenProvider {
    @Override
    public GreenionToken createToken(String uid, Date now, String secretKey) {
        long tokenPeriod = 60 * 1000 * 10L;

        Claims claims = Jwts.claims().setSubject(uid);

        return new GreenionToken(
                Jwts.builder().setClaims(claims)
                        .setIssuedAt(now)
                        .setExpiration(new Date(now.getTime() + tokenPeriod))
                        .signWith(SignatureAlgorithm.HS256, secretKey)
                        .compact());
    }
}
