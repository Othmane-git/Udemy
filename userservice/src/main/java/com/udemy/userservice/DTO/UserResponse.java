package com.udemy.userservice.DTO;

import lombok.Data;
import com.udemy.userservice.Model.UserRole;

import java.time.LocalDateTime;

@Data
public class UserResponse {
    private String id;

    private String firstName;
    private String lastName;


    private String email;

    private UserRole role;

    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
