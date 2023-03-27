package com.smartlabs.projecttender.services.interfaces;

import com.smartlabs.projecttender.dtos.requests.CreateProjectRequest;
import com.smartlabs.projecttender.dtos.requests.UpdateProjectRequest;
import com.smartlabs.projecttender.dtos.responses.ProjectResponse;

public interface IProjectService {

    ProjectResponse create (CreateProjectRequest request);

    ProjectResponse get (Long id);

    ProjectResponse list (Long projectId);

    ProjectResponse update (Long id, UpdateProjectRequest request);

    ProjectResponse delete (Long id);
}
