package com.blackship.greenion.user.oauth2;

public interface TokenService {
    void verifyToken(String token);
    GreenionToken generateToken(String uid);
}
