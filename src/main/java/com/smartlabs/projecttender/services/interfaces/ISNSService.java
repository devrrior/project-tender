package com.smartlabs.projecttender.services.interfaces;

import com.smartlabs.projecttender.entities.Project;

public interface ISNSService {
    void subscribeEmail(String email);

    void sendNotification(Project project);
}
