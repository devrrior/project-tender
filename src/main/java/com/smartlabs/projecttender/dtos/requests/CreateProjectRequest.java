package com.smartlabs.projecttender.dtos.requests;

import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class CreateProjectRequest {

    @Email
    private String email;

    private String companyName;

    private String password;

}
