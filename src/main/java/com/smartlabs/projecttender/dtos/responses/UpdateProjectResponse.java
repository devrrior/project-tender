package com.smartlabs.projecttender.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProjectResponse {
    private Long id;

    private String name;

    private String description;

    private Float budget;

    private Enum category;

    private String imageUrl;
}
