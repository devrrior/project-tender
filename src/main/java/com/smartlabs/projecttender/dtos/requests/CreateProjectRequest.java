package com.smartlabs.projecttender.dtos.requests;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateProjectRequest {

    private Long id;

    private String name;

    private String description;

    private Float budget;

    private String type;

    private String imageUrl;
}
