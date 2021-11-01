package com.blackship.greenion.sign;

public class SpyUserClient implements UserClient {
    public RemoteUser getUser_returnsValue;
    public String getUser_argumentsEmail;

    @Override
    public RemoteUser getUser(String email) {
        getUser_argumentsEmail = email;
        return getUser_returnsValue;
    }
}
