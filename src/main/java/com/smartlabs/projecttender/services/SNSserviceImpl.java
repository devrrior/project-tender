package com.smartlabs.projecttender.services;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.smartlabs.projecttender.entities.Project;
import com.smartlabs.projecttender.services.interfaces.ISNSservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SNSserviceImpl implements ISNSservice {
    @Autowired
     private AmazonSNSClient amazonSNSClient;
    @Override
    public void subscribeEmail(String topicArn, String email) {
        SubscribeRequest request = new SubscribeRequest(topicArn, "email", email)
                .withProtocol("email")
                .withReturnSubscriptionArn(true);
        amazonSNSClient.subscribe(request);

    }

    @Override
    public void sendNotification(Project project, String topicArn) {
        String projectName= project.getName();
        String message = "Se ha creado un nuevo proyecto" + projectName ;
        System.out.println(projectName);
        System.out.println(message);

        PublishRequest publishRequest = new PublishRequest()
                .withTopicArn(topicArn)
                .withMessage(message)
                .withSubject("Nuevo projecto: "+ projectName);
        amazonSNSClient.publish(publishRequest);

    }
}
