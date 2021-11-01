package com.blackship.greenion.sign;

public class SpySignService implements SignService {
    public SignUser signUp_argumentSignUser;
    public boolean signUp_returnValue;

    @Override
    public boolean signUp(SignUser signUser) {
        signUp_argumentSignUser = signUser;
        return signUp_returnValue;
    }
}
