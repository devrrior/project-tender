package com.smartlabs.projecttender.dtos.requests;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateProjectRequest {
    private Long id;

    private String responsibleConstructor;
}
