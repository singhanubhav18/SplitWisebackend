package com.splitwise.auth_service.service;

import com.splitwise.auth_service.dto.AuthResponseDto;
import com.splitwise.auth_service.dto.LoginRequestDto;
import com.splitwise.auth_service.dto.SignupRequestDto;
import com.splitwise.auth_service.dto.UserDetailsDto;
import com.splitwise.auth_service.entity.User;
import com.splitwise.auth_service.exception.BadRequestException;
import com.splitwise.auth_service.exception.ResourceNotFoundException;
import com.splitwise.auth_service.repository.UserRepository;
import com.splitwise.auth_service.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<UserDetailsDto> getByName(String name) {
        // Fetch name from the database
        try {
            List<User> users = userRepository.findByName(name);
            if (users.isEmpty()) {
                throw new ResourceNotFoundException("No users found with name containing: " + name);
            }
            // Map the User entity to UserDetailsDto
            return users.stream()
                    .map(user -> modelMapper.map(user, UserDetailsDto.class))
                    .collect(Collectors.toList());
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("No users found with name containing: " + name);
        }
    }
}
