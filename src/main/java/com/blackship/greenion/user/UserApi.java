package com.blackship.greenion.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApi {

    @GetMapping("/users")
    public HttpStatus getUsers() {
        return HttpStatus.OK;
    }
}
