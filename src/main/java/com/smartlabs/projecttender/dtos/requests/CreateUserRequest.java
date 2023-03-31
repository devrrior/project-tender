package com.smartlabs.projecttender.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUserRequest {

    private String email;

    private String name;

    private String password;
}
