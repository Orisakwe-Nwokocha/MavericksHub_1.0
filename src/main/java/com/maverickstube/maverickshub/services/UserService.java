package com.maverickstube.maverickshub.services;

import com.maverickstube.maverickshub.data.models.User;
import com.maverickstube.maverickshub.dto.requests.GetUserRequest;
import com.maverickstube.maverickshub.dto.requests.RegisterRequest;
import com.maverickstube.maverickshub.dto.responses.GetUserResponse;
import com.maverickstube.maverickshub.dto.responses.RegisterResponse;

import java.util.List;

public interface UserService {
    RegisterResponse register(RegisterRequest request);

    GetUserResponse getUserById(GetUserRequest request);

    List<GetUserResponse> findAll();

    User findBy(Long id);
}
