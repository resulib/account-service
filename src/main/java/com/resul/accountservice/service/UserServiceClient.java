package com.resul.accountservice.service;

import com.resul.accountservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service", url = "http://localhost:8090/api/v1/users")
public interface UserServiceClient {

    @GetMapping("/by-username/{username}")
    UserDto getUserByUsername(@PathVariable("username") String username);

    @GetMapping("/{id}")
    UserDto getUserById(@PathVariable("id") Long id);

}