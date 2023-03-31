package com.smartlabs.projecttender.rabbit;

import com.smartlabs.projecttender.dtos.requests.CreateProjectRequest;
import com.smartlabs.projecttender.dtos.requests.CreateUserRequest;
import com.smartlabs.projecttender.dtos.requests.GetProjectRequest;
import com.smartlabs.projecttender.dtos.requests.UpdateProjectRequest;
import com.smartlabs.projecttender.services.interfaces.IProjectService;
import com.smartlabs.projecttender.services.interfaces.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {

    private final IProjectService projectService;


    private final IUserService userService;

    public Consumer(IProjectService projectService, IUserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @RabbitListener(queues = {"${queue.projectList.get}"})
    public void getProjectList(){
        projectService.list();
    }

    @RabbitListener(queues = {"${queue.project.create}"})
    public void createProject(CreateProjectRequest request){
        projectService.create(request);
    }
    @RabbitListener(queues = {"${queue.project.get}"})
    public void getProject(GetProjectRequest request){
        projectService.get(request.getId());
    }

    @RabbitListener(queues = {"${queue.project.update}"})
    public void updateProject(UpdateProjectRequest request){
        projectService.update(request.getId(), request);
    }

    @RabbitListener(queues = {"${queue.user.create}"})
    public void createUser(CreateUserRequest request){
        userService.create(request);
    }
}
