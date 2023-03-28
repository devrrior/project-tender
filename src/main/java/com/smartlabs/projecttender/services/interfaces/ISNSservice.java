package com.smartlabs.projecttender.services.interfaces;

import com.smartlabs.projecttender.entities.Project;

public interface ISNSservice {
    void subscribeEmail(String topicArn, String email);

    void sendNotification(Project project, String topicArn);
}
