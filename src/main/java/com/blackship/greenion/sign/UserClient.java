package com.blackship.greenion.sign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient(url = "/users")
public interface UserClient {
    RemoteUser getUser(String email);
}
