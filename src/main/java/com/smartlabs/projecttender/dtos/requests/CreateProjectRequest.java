package com.smartlabs.projecttender.dtos.requests;

import com.smartlabs.projecttender.enums.ProjectType;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateProjectRequest {

    private Long id;

    private String name;

    private String description;

    private Float budget;

    private ProjectType type;

    private String imageUrl;
}
