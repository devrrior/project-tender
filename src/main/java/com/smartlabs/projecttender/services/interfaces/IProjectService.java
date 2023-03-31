package com.smartlabs.projecttender.services.interfaces;

import com.smartlabs.projecttender.dtos.requests.CreateProjectRequest;
import com.smartlabs.projecttender.dtos.requests.UpdateProjectRequest;
import com.smartlabs.projecttender.dtos.responses.CreateProjectResponse;
import com.smartlabs.projecttender.dtos.responses.GetProjectResponse;
import com.smartlabs.projecttender.dtos.responses.UpdateProjectResponse;

import java.util.List;

public interface IProjectService {

    CreateProjectResponse create(CreateProjectRequest request);

    GetProjectResponse get(Long id);

    List<GetProjectResponse> list();

    UpdateProjectResponse update(Long id, UpdateProjectRequest request);

}
