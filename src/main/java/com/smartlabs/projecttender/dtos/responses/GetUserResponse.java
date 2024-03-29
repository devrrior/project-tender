package com.smartlabs.projecttender.dtos.responses;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserResponse {
    private Long id;

    private String email;

    private String name;

    private String password;
}
