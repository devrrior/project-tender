package com.smartlabs.projecttender.dtos.requests;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class UpdateUserRequest {
    private Long id;

    private String email;

    private String companyName;

    private String password;
}

