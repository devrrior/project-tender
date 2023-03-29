package com.smartlabs.projecttender.services;

import com.smartlabs.projecttender.dtos.requests.CreateUserRequest;
import com.smartlabs.projecttender.dtos.responses.CreateUserResponse;
import com.smartlabs.projecttender.dtos.responses.GetUserResponse;
import com.smartlabs.projecttender.entities.User;
import com.smartlabs.projecttender.rabbit.Publisher;
import com.smartlabs.projecttender.repositories.IUserRepository;
import com.smartlabs.projecttender.services.interfaces.ISNSService;
import com.smartlabs.projecttender.services.interfaces.IUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository repository;

    private final ISNSService snsService;

    private final Publisher publisher;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserServiceImpl(IUserRepository repository, Publisher publisher, ISNSService snsService) {
        this.repository = repository;
        this.publisher = publisher;
        this.snsService = snsService;
    }

    @Override
    public CreateUserResponse create(CreateUserRequest request) {
        User user = repository.save(from(request));
        snsService.subscribeEmail(user.getEmail());

        CreateUserResponse createUserResponse = toCreateUserResponse(user);

        String routingKey = "user.new";
        publisher.send(createUserResponse, routingKey);

        return createUserResponse;
    }

    @Override
    public GetUserResponse get(Long id) {
        User user = findAndEnsureExist(id);
        GetUserResponse getUserResponse = from(user);

        String routingKey = "user.current";
        publisher.send(getUserResponse, routingKey);

        return getUserResponse;
    }

    private User from(CreateUserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(encoder.encode(request.getPassword()));
        return user;
    }

    private CreateUserResponse toCreateUserResponse(User user) {
        CreateUserResponse response = new CreateUserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setName(user.getName());
        response.setPassword(user.getPassword());
        return response;
    }

    private GetUserResponse from(User user) {
        GetUserResponse response = new GetUserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setName(user.getName());
        response.setPassword(user.getPassword());
        return response;
    }

    private User findAndEnsureExist(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

}
