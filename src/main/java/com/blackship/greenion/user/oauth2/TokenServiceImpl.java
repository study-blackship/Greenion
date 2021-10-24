package com.blackship.greenion.user.oauth2;

import com.blackship.greenion.util.DateProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
    private final DateProvider dateProvider;

    public TokenServiceImpl(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
    }

    @Override
    public void verifyToken(String token) {
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey("secretKey")
                .parseClaimsJws(token);

        if (claims.getBody().getExpiration()
                .before(dateProvider.now())) {
            throw new TokenExpiredException();
        }
    }

    @Override
    public GreenionToken generateToken(String uid) {
        return null;
    }
}
