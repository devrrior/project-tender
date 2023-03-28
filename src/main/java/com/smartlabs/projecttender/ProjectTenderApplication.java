package com.smartlabs.projecttender;

import com.amazonaws.services.sns.AmazonSNSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication

public class ProjectTenderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectTenderApplication.class, args);
    }

}
