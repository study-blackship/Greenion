package com.blackship.greenion.sign;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignServiceImpl implements SignService {

    private final UserClient userClient;

    @Override
    public boolean signUp(SignUser signUser) {
        RemoteUser remoteUser = userClient.getUser(signUser.getEmail());
        if (RemoteUser.EMPTY.equals(remoteUser)) {
            throw new ExistsEmailException();
        }

        return true;
    }
}
