package com.blackship.greenion.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApi {
    @GetMapping
    public String demo() {
        return "Demo";
    }
}
