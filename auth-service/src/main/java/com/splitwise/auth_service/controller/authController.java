package com.splitwise.auth_service.controller;

import com.splitwise.auth_service.dto.AuthResponseDto;
import com.splitwise.auth_service.dto.LoginRequestDto;
import com.splitwise.auth_service.dto.SignupRequestDto;
import com.splitwise.auth_service.dto.UserDetailsDto;
import com.splitwise.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class authController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDto> signUp(@RequestBody SignupRequestDto signupRequestDto) {
        AuthResponseDto authResponse = authService.signUp(signupRequestDto);
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        AuthResponseDto authResponse = authService.login(loginRequestDto);
        return ResponseEntity.ok(authResponse);
    }

    @GetMapping("/getUserByName/{name}")
    public List<UserDetailsDto> getUserByName(@PathVariable("name") String name) {
        return authService.getByName(name);
    }
}
