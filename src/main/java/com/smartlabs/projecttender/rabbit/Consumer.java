package com.smartlabs.projecttender.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {
    @RabbitListener(queues = {"${project.queue.projectList.get}"})
    public void getProjectList(@Payload String message){
        log.info("List projects requested {}", message);

        makeslow();
    }

    @RabbitListener(queues = {"${project.queue.project.create}"})
    public void createProject(@Payload String message){
        log.info("Project created {}", message);

        makeslow();
    }
    @RabbitListener(queues = {"${project.queue.project.get}"})
    public void getProject(@Payload String message){
        log.info(" Get project {}", message);

        makeslow();
    }

    @RabbitListener(queues = {"${project.queue.project.update}"})
    public void updateProject(@Payload String message){
        log.info("Project Updated {}", message);

        makeslow();
    }
    @RabbitListener(queues = {"${user.queue.user.create}"})
    public void createUser(@Payload String message){
        log.info("User created {}", message);

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
