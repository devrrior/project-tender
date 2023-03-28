package com.smartlabs.projecttender.dtos.requests;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class CreateUserRequest {

    private String email;

    private String name;

    private String password;
}
