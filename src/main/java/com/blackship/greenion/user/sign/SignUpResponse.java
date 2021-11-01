package com.blackship.greenion.user.sign;

import lombok.Getter;

@Getter
public class SignUpResponse {
    private final boolean result;
    private final String message;
    private final String email;

    private SignUpResponse(boolean result, String message, String email) {
        this.result = result;
        this.message = message;
        this.email = email;
    }

    public static SignUpResponse of(boolean result, String email) {
        return result ? new SignUpResponse(true, "success", email)
        : new SignUpResponse(false, "fail", email);
    }
}
