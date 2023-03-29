package com.smartlabs.projecttender.services;

import com.smartlabs.projecttender.dtos.requests.CreateProjectRequest;
import com.smartlabs.projecttender.dtos.requests.UpdateProjectRequest;
import com.smartlabs.projecttender.dtos.responses.CreateProjectResponse;
import com.smartlabs.projecttender.dtos.responses.GetProjectResponse;
import com.smartlabs.projecttender.dtos.responses.UpdateProjectResponse;
import com.smartlabs.projecttender.entities.Project;
import com.smartlabs.projecttender.rabbit.Publisher;
import com.smartlabs.projecttender.repositories.IProjectRepository;
import com.smartlabs.projecttender.services.interfaces.IProjectService;
import com.smartlabs.projecttender.services.interfaces.ISNSService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements IProjectService {
    private final IProjectRepository repository;

    private final ISNSService snsService;

    private final Publisher publisher;

    public ProjectServiceImpl(IProjectRepository repository, ISNSService snsService, Publisher publisher) {
        this.repository = repository;
        this.snsService = snsService;
        this.publisher = publisher;
    }

    @Override
    public CreateProjectResponse create(CreateProjectRequest request) {
        Project project = repository.save(from(request));
        CreateProjectResponse createProjectResponse = toCreateProjectResponse(project);

        String routingKey = "project.new";
        publisher.send(createProjectResponse, routingKey);

        snsService.sendNotification(project);

        return createProjectResponse;
    }

    @Override
    public GetProjectResponse get(Long id) {
        Project project = findAndEnsureExist(id);
        GetProjectResponse getProjectResponse = toGetProjectResponse(project);

        String routingKey = "project.new";
        publisher.send(getProjectResponse, routingKey);

        return getProjectResponse;
    }

    @Override
    public List<GetProjectResponse> list() {
        List<GetProjectResponse> getProjectResponseList = repository
                .findAll()
                .stream()
                .map(this::toGetProjectResponse)
                .collect(Collectors.toList());

        String routingKey = "projectList.new";
        publisher.send(getProjectResponseList, routingKey);

        return getProjectResponseList;
    }

    public UpdateProjectResponse update(Long id, UpdateProjectRequest request) {
        Project project = findAndEnsureExist(id);

        project.setResponsibleConstructor(request.getResponsibleConstructor());

        Project savedProject = repository.save(project);
        return toUpdateProjectResponse(savedProject);
    }


    private Project from(CreateProjectRequest request) {
        Project project = new Project();
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setBudget(request.getBudget());
        project.setType(request.getType());
        project.setImageUrl(request.getImageUrl());
        return project;
    }

    private CreateProjectResponse toCreateProjectResponse(Project project) {
        CreateProjectResponse response = new CreateProjectResponse();
        response.setName(project.getName());
        response.setDescription(project.getDescription());
        response.setBudget(project.getBudget());
        response.setType(project.getType());
        response.setImageUrl(project.getImageUrl());

        return response;
    }

    private GetProjectResponse toGetProjectResponse(Project project) {
        GetProjectResponse response = new GetProjectResponse();
        response.setName(project.getName());
        response.setDescription(project.getDescription());
        response.setBudget(project.getBudget());
        response.setType(project.getType());
        response.setImageUrl(project.getImageUrl());

        return response;
    }

    private UpdateProjectResponse toUpdateProjectResponse(Project project) {
        UpdateProjectResponse response = new UpdateProjectResponse();
        response.setName(project.getName());
        response.setDescription(project.getDescription());
        response.setBudget(project.getBudget());
        response.setType(project.getType());
        response.setImageUrl(project.getImageUrl());

        return response;

    }

    private Project findAndEnsureExist(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("project not found"));
    }
}
