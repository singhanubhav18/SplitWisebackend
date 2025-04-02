package com.splitwise.auth_service.utils;

import com.splitwise.auth_service.dto.SignupRequestDto;
import com.splitwise.auth_service.exception.BadRequestException;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // Hash a password for the first time
    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    // Check that a plain text password matches a previously hashed one
    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }

    public static void validateSignupRequest(SignupRequestDto signupRequestDto) {
        if (signupRequestDto.getName() == null || signupRequestDto.getName().isEmpty()) {
            throw new BadRequestException("Name cannot be empty.");
        }
        if (signupRequestDto.getEmail() == null || signupRequestDto.getEmail().isEmpty()) {
            throw new BadRequestException("Email cannot be empty.");
        }
        if (signupRequestDto.getPassword() == null || signupRequestDto.getPassword().isEmpty()) {
            throw new BadRequestException("Password cannot be empty .");
        }
        if (signupRequestDto.getPhoneNumber() == null || signupRequestDto.getPhoneNumber().isEmpty()) {
            throw new BadRequestException("PhoneNumber cannot be empty.");
        } else if (signupRequestDto.getPhoneNumber().length()<=8) {
            throw new BadRequestException("PhoneNumber cannot be less than 8 characters.");
        }
    }



}
