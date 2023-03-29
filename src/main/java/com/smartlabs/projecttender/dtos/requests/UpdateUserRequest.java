package com.smartlabs.projecttender.dtos.requests;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateUserRequest {
    private Long id;

    private String email;

    private String name;

    private String password;
}

