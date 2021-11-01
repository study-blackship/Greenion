package com.blackship.greenion.user.oauth2.token;

import java.util.Date;

public interface TokenProvider {
    GreenionToken createToken(String uid, Date now, String secretKey);
}

