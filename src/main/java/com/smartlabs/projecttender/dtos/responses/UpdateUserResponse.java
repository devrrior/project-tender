package com.smartlabs.projecttender.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserResponse {
    private Long id;

    private String email;

    private String companyName;

    private String password;
}
