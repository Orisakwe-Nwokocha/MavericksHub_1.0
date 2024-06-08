package com.maverickstube.maverickshub.services.impls;

import com.maverickstube.maverickshub.data.models.User;
import com.maverickstube.maverickshub.data.repositories.UserRepository;
import com.maverickstube.maverickshub.dto.requests.GetUserRequest;
import com.maverickstube.maverickshub.dto.requests.RegisterRequest;
import com.maverickstube.maverickshub.dto.responses.GetUserResponse;
import com.maverickstube.maverickshub.dto.responses.RegisterResponse;
import com.maverickstube.maverickshub.exceptions.InvalidStateException;
import com.maverickstube.maverickshub.exceptions.UserNotFoundException;
import com.maverickstube.maverickshub.services.UserService;

import static com.maverickstube.maverickshub.utils.MapperSingleton.MAPPER;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MavericksHubUserService implements UserService {
    private final UserRepository userRepository;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        User newUser = MAPPER.map(request, User.class);
        User savedUser = userRepository.save(newUser);
        var response = MAPPER.map(savedUser, RegisterResponse.class);
        response.setMessage("User registered successfully");
        return response;
    }

    @Override
    public GetUserResponse getUserById(GetUserRequest request) {
        User foundUser = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User with id " + request.getUserId() + " not found"));
        return MAPPER.map(foundUser, GetUserResponse.class);
    }

    @Override
    public List<GetUserResponse> findAllUsers() {
        var foundUsers = userRepository.findAll();
        if (foundUsers.isEmpty()) throw new InvalidStateException("No users found in the database");
        return List.of(MAPPER.map(foundUsers, GetUserResponse[].class));
    }

    @Override
    public User findUserBy(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(
                        String.format("User with id %s not found", id)
                        ));
    }
}
