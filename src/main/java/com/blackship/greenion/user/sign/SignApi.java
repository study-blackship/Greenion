package com.blackship.greenion.user.sign;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignApi {

    private final SignService signService;

    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody  SignUser signUser) {
        boolean isSignUpSuccess = signService.signUp(signUser);

        return createSignUpResponse(signUser, isSignUpSuccess);
    }

    private ResponseEntity<SignUpResponse> createSignUpResponse(SignUser signUser, boolean isSignUpSuccess) {
        if (isSignUpSuccess) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(SignUpResponse.of(
                            true,
                            signUser.getEmail()
                    ));
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(SignUpResponse.of(
                        false,
                        signUser.getEmail()
                ));
    }
}