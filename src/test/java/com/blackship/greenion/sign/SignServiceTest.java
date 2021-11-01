package com.blackship.greenion.sign;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class SignServiceTest {

    private SignService signService;
    private SpyUserClient spyUserClient;

    @BeforeEach
    void setUp() {
        spyUserClient = new SpyUserClient();
        signService = new SignServiceImpl(spyUserClient);
    }

    @Test
    void signUp_throwsExistsEmailException_when_alreadyExistsEmail() {
        SignUser givenSignUser = new SignUser("email@email.com");

        spyUserClient.getUser_returnsValue = RemoteUser.EMPTY;

        Throwable exception = catchThrowable(() -> signService.signUp(givenSignUser));

        assertThat(exception)
                .isInstanceOf(ExistsEmailException.class)
                .hasMessage("ExistsEmailException");
    }

    @Test
    void signUp_callsGetUserInUserClient() {
        SignUser givenSignUser = new SignUser("email@email.com");

        signService.signUp(givenSignUser);

        assertThat(spyUserClient.getUser_argumentsEmail).isEqualTo(givenSignUser.getEmail());
    }

    @Test
    void signUp_returnsTrue_when_successSignUp() {
        SignUser givenSignUser = new SignUser("email@email.com");

        spyUserClient.getUser_returnsValue = new RemoteUser(givenSignUser.getEmail());

        assertThat(signService.signUp(givenSignUser)).isTrue();
    }
}