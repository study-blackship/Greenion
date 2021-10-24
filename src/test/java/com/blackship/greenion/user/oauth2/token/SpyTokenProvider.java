package com.blackship.greenion.user.oauth2.token;

import java.util.Date;

public class SpyTokenProvider implements TokenProvider {
    public String createToken_argumentsUid;
    public Date createToken_argumentDate;
    public GreenionToken createToken_returnValue;

    @Override
    public GreenionToken createToken(String uid, Date now, String secretKey) {
        createToken_argumentsUid = uid;
        createToken_argumentDate = now;
        return createToken_returnValue;
    }
}
