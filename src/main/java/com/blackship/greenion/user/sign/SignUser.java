package com.blackship.greenion.user.sign;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@EqualsAndHashCode
@NoArgsConstructor(access = PRIVATE)
@Getter
public class SignUser {
    private String email;

    public SignUser(String email) {
        this.email = email;
    }

}
