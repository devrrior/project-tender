package com.smartlabs.projecttender.dtos.requests;

import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class UpdateProjectRequest {
    private String name;

    private String description;

    private Float budget;

    private Enum category;

    private String imageUrl;
}
