package com.smartlabs.projecttender.dtos.requests;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class CreateProjectRequest {

    private Long id;
    private String name;

    private String description;

    private Float budget;

    private Enum category;

    private String imageUrl;


}
