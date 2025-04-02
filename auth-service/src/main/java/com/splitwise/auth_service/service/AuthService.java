package com.splitwise.auth_service.service;

import com.splitwise.auth_service.dto.AuthResponseDto;
import com.splitwise.auth_service.dto.LoginRequestDto;
import com.splitwise.auth_service.dto.SignupRequestDto;
import com.splitwise.auth_service.entity.User;
import com.splitwise.auth_service.exception.BadRequestException;
import com.splitwise.auth_service.exception.ResourceNotFoundException;
import com.splitwise.auth_service.repository.UserRepository;
import com.splitwise.auth_service.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    public AuthResponseDto signUp(SignupRequestDto signupRequestDto) {
        boolean exists = userRepository.existsByEmail(signupRequestDto.getEmail());
        PasswordUtil.validateSignupRequest(signupRequestDto);
        if(exists) {
            throw new BadRequestException("User already exists, cannot signup again.");
        }

        User user = modelMapper.map(signupRequestDto, User.class);
        user.setPassword(PasswordUtil.hashPassword(signupRequestDto.getPassword()));

        User savedUser = userRepository.save(user);
        String token = jwtService.generateAccessToken(savedUser);
        return new AuthResponseDto(true,"Successfully user created", new AuthResponseDto.Response(token));
    }

    public AuthResponseDto login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: "+loginRequestDto.getEmail()));

        boolean isPasswordMatch = PasswordUtil.checkPassword(loginRequestDto.getPassword(), user.getPassword());

        if(!isPasswordMatch) {
            throw new BadRequestException("Incorrect password");
        }

        String token = jwtService.generateAccessToken(user);

        return new AuthResponseDto(true,"Successfully user login", new AuthResponseDto.Response(token));
    }
}
