package com.blackship.greenion.user.sign;

public class SpySignService implements SignService {
    public SignUser signUp_argumentSignUser;

    @Override
    public boolean signUp(SignUser signUser) {
        signUp_argumentSignUser = signUser;
        return false;
    }
}
