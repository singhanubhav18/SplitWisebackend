package com.splitwise.auth_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Column(nullable = false)
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Id
    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @Column(nullable = false)
    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @Column(nullable = false)
    @NotEmpty(message = "Phone number cannot be empty")
    private String phoneNumber;

    private String role;
    private String imageUrl;
}
