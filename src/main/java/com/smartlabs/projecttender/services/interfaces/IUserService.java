package com.smartlabs.projecttender.services.interfaces;

import com.smartlabs.projecttender.dtos.requests.CreateUserRequest;
import com.smartlabs.projecttender.dtos.responses.CreateUserResponse;
import com.smartlabs.projecttender.dtos.responses.GetUserResponse;

public interface IUserService {
    CreateUserResponse create(CreateUserRequest request);

    GetUserResponse get(Long id);

}
