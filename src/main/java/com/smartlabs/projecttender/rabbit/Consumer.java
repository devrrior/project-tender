package com.smartlabs.projecttender.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {
    @RabbitListener(queues = {"${project.queue.Listget}"})
    public void Listget(@Payload String message){
        log.info("List projects requested {}", message);

        makeslow();
    }
    @RabbitListener(queues = {"${project.queue.Listnew}"})
    public void Listnew(@Payload String message){
        log.info("List projects response {}", message);

        makeslow();
    }
    @RabbitListener(queues = {"${project.queue.create}"})
    public void CreateProject(@Payload String message){
        log.info("Project created {}", message);

        makeslow();
    }
    @RabbitListener(queues = {"${project.queue.get}"})
    public void GetProject(@Payload String message){
        log.info(" Get project {}", message);

        makeslow();
    }
    @RabbitListener(queues = {"${project.queue.new}"})
    public void NewProject(@Payload String message){
        log.info("New Project {}", message);

        makeslow();
    }
    @RabbitListener(queues = {"${project.queue.update}"})
    public void UpdateProject(@Payload String message){
        log.info("Project Updated {}", message);

        makeslow();
    }
    @RabbitListener(queues = {"${user.queue.create}"})
    public void CreateUser(@Payload String message){
        log.info("User created {}", message);

        makeslow();
    }

    @RabbitListener(queues = {"${user.queue.new}"})
    public void NewUser(@Payload String message){
        log.info("Received message {}", message);

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
