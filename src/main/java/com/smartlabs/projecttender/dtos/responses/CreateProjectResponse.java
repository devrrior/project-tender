package com.smartlabs.projecttender.dtos.responses;

import com.smartlabs.projecttender.enums.ProjectType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProjectResponse {

    private Long id;

    private String name;

    private String description;

    private Float budget;

    private ProjectType type;

    private String imageUrl;
}
