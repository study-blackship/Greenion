package com.blackship.greenion.sign;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
@Getter
public class RemoteUser {
    private String email;

    public RemoteUser(String email) {
        this.email = email;
    }

    public static final RemoteUser EMPTY = new RemoteUser();
}
