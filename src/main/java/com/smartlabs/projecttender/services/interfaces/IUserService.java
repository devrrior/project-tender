package com.smartlabs.projecttender.services.interfaces;

import com.smartlabs.projecttender.dtos.requests.CreateUserRequest;
import com.smartlabs.projecttender.dtos.requests.UpdateUserRequest;
import com.smartlabs.projecttender.dtos.responses.GetUserResponse;
import com.smartlabs.projecttender.dtos.responses.UpdateUserResponse;

import java.util.List;

public interface IUserService {
    GetUserResponse create (CreateUserRequest request);

    GetUserResponse get (Long id);

    List <GetUserResponse> list();

    UpdateUserResponse update (Long id, UpdateUserRequest request);

    String delete (Long id);
}
