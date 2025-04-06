package com.splitwise.transaction_service.client;

import com.splitwise.transaction_service.dto.UserDetailsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name="auth-service",path = "/user")
public interface AuthServiceClient {

    @GetMapping("auth/getUserByName/{name}")
    List<UserDetailsDto> getUserByName(@PathVariable("name") String name);
}
