package com.smartlabs.projecttender.services;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.smartlabs.projecttender.entities.Project;
import com.smartlabs.projecttender.services.interfaces.ISNSService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SNSServiceImpl implements ISNSService {

    @Value("${sns.arn}")
    private String SNS_ARN;
    private final AmazonSNSClient amazonSNSClient;

    public SNSServiceImpl(AmazonSNSClient amazonSNSClient) {
        this.amazonSNSClient = amazonSNSClient;
    }

    @Override
    public void subscribeEmail(String email) {
        SubscribeRequest request = new SubscribeRequest(SNS_ARN, "email", email)
                .withProtocol("email").withReturnSubscriptionArn(true);
        amazonSNSClient.subscribe(request);

    }

    @Override
    public void sendNotification(Project project) {
        String projectName = project.getName();
        String message = "Se ha generado un nuevo proyecto" + projectName;
        System.out.println(projectName);
        System.out.println(message);

        PublishRequest publishRequest = new PublishRequest()
                .withTopicArn(SNS_ARN)
                .withMessage(message)
                .withSubject("Nuevo projecto: " + projectName);
        amazonSNSClient.publish(publishRequest);

    }
}
