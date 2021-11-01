package com.blackship.greenion.user.sign;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignApi {

    private final SignService signService;

    @PostMapping("/signUp")
    public ResponseEntity<Void> signUp(@RequestBody  SignUser signUser) {
        signService.signUp(signUser);
        return ResponseEntity.ok().build();
    }

}
