package com.smartlabs.projecttender.rabbit;

import com.smartlabs.projecttender.dtos.requests.CreateProjectRequest;
import com.smartlabs.projecttender.dtos.requests.CreateUserRequest;
import com.smartlabs.projecttender.dtos.requests.UpdateProjectRequest;
import com.smartlabs.projecttender.dtos.responses.GetProjectResponse;
import com.smartlabs.projecttender.services.interfaces.IProjectService;
import com.smartlabs.projecttender.services.interfaces.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class Consumer {

    @Autowired
    private IProjectService projectService;


    private IUserService userService;
    @RabbitListener(queues = {"${project.queue.projectList.get}"})
    public void getProjectList(@Payload List<GetProjectResponse> response){
        log.info("List projects requested {}", response);
        projectService.list();
        makeslow();
    }

    @RabbitListener(queues = {"${project.queue.project.create}"})
    public void createProject(@Payload CreateProjectRequest request){
        log.info("Project created {}", request);
        projectService.create(request);
        makeslow();
    }
    @RabbitListener(queues = {"${project.queue.project.get}"})
    public void getProject(@Payload GetProjectResponse response){
        log.info(" Get project {}", response);
        projectService.get(response.getId());
        makeslow();
    }

    @RabbitListener(queues = {"${project.queue.project.update}"})
    public void updateProject(@Payload UpdateProjectRequest request){
        log.info("Project Updated {}", request);
        projectService.update(request.getId(), request);
        makeslow();
    }
    @RabbitListener(queues = {"${user.queue.user.create}"})
    public void createUser(@Payload CreateUserRequest request){
        log.info("User created {}", request);
        userService.create(request);
        makeslow();
    }




    private void makeslow(){
        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
