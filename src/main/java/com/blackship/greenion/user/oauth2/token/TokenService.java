package com.blackship.greenion.user.oauth2.token;

public interface TokenService {
    void verifyToken(String token);
    GreenionToken generateToken(String uid);
    String extractUid(String token);
}
