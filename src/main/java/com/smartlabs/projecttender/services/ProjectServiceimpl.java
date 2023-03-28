package com.smartlabs.projecttender.services;

import com.smartlabs.projecttender.dtos.requests.CreateProjectRequest;
import com.smartlabs.projecttender.dtos.requests.UpdateProjectRequest;
import com.smartlabs.projecttender.dtos.responses.GetProjectResponse;
import com.smartlabs.projecttender.dtos.responses.UpdateProjectResponse;
import com.smartlabs.projecttender.entities.Project;
import com.smartlabs.projecttender.entities.User;
import com.smartlabs.projecttender.repositories.IProjectRepository;
import com.smartlabs.projecttender.services.interfaces.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProjectServiceimpl implements IProjectService {
    @Autowired
    private IProjectRepository repository;

    @Override
    public GetProjectResponse create (CreateProjectRequest request){
        Project project = from(request);
        return from(repository.save(project));

    }

    @Override
    public GetProjectResponse get(Long id) {
        Project project = findAndEnsureExist(id);
        return from(project);
    }

    @Override
    public List<GetProjectResponse> list() {
        return repository
                .findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public String delete(Long id){
        Project project = findAndEnsureExist(id);
        repository.deleteById(id);

        return "Project deleted correctly";
    }

    public UpdateProjectResponse update(Long id, UpdateProjectRequest request){
        Project project = findAndEnsureExist(id);
        project.setId(project.getId());
        project.setName(project.getName());
        project.setDescription(project.getDescription());
        project.setBudget(project.getBudget());
        project.setCategory(project.getCategory());
        project.setImageUrl(project.getImageUrl());
        Project saveProject = repository.save(project);
        return toUpdateProjectResponse(project);
    }



    private Project from( CreateProjectRequest request){
        Project project = new Project();
        project.setName(project.getName());
        project.setDescription(project.getDescription());
        project.setBudget(project.getBudget());
        project.setCategory(project.getCategory());
        project.setImageUrl(project.getImageUrl());
        return project;
    }

    private GetProjectResponse from(Project project){
        GetProjectResponse response = new GetProjectResponse();
        project.setName(project.getName());
        project.setDescription(project.getDescription());
        project.setBudget(project.getBudget());
        project.setCategory(project.getCategory());
        project.setImageUrl(project.getImageUrl());
        return  response;
    }

    private UpdateProjectResponse toUpdateProjectResponse(Project project){
        UpdateProjectResponse response = new UpdateProjectResponse();
        project.setName(project.getName());
        project.setDescription(project.getDescription());
        project.setBudget(project.getBudget());
        project.setCategory(project.getCategory());
        project.setImageUrl(project.getImageUrl());
        return  response;

    }
    private Project findAndEnsureExist (Long id){
        return repository.findById(id).orElseThrow(()-> new RuntimeException("project not found"));
    }
}
