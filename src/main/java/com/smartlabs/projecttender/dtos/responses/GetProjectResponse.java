package com.smartlabs.projecttender.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProjectResponse {
    private Long id;

    private String name;

    private String description;

    private Float budget;

    private String type;

    private String imageUrl;

    private String responsibleConstructor;
}
