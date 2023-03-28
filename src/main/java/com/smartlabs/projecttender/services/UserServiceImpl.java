package com.smartlabs.projecttender.services;

import com.smartlabs.projecttender.dtos.requests.CreateUserRequest;
import com.smartlabs.projecttender.dtos.requests.UpdateUserRequest;
import com.smartlabs.projecttender.dtos.responses.*;
import com.smartlabs.projecttender.entities.User;
import com.smartlabs.projecttender.repositories.IUserRepository;
import com.smartlabs.projecttender.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository repository;

    @Override
    public GetUserResponse create(CreateUserRequest request){
        User user = from(request);
        return from(repository.save(user));
    }
    @Override
    public String delete(Long id){
        User user = findAndEnsureExist(id);
        repository.deleteById(id);

        return "User deleted correctly";
    }
    @Override
    public UpdateUserResponse  update( Long id, UpdateUserRequest request){
        User user = findAndEnsureExist(id);
        user.setEmail(request.getEmail());
        user.setName(request.getCompanyName());
        user.setPassword(request.getPassword());
        User saveUser = repository.save(user);
        return toUpdateUserResponse(saveUser);

    }
    @Override
    public List<GetUserResponse>list(){
        return repository
                .findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }


    @Override
    public GetUserResponse get(Long id){
        User user = findAndEnsureExist(id);
        return from(user);
    }

    private User from(CreateUserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getCompanyName());
        user.setPassword(request.getPassword());
        return user;
    }
    private GetUserResponse from(User user){
       GetUserResponse response = new GetUserResponse();
       response.setId(response.getId());
       response.setEmail(response.getEmail());
       response.setCompanyName(response.getCompanyName());
       response.setPassword(response.getPassword());
        return response;
    }
    private UpdateUserResponse toUpdateUserResponse(User user){
        UpdateUserResponse response = new UpdateUserResponse();
        response.setId(response.getId());
        response.setEmail(response.getEmail());
        response.setCompanyName(response.getCompanyName());
        response.setPassword(response.getPassword());
        return response;
    }



    private User findAndEnsureExist (Long id){
        return repository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
    }

}
