package com.maverickstube.maverickshub.services;

import com.maverickstube.maverickshub.dto.requests.GetUserRequest;
import com.maverickstube.maverickshub.dto.requests.RegisterRequest;
import com.maverickstube.maverickshub.dto.responses.GetUserResponse;
import com.maverickstube.maverickshub.dto.responses.RegisterResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void registerTest() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("test@email.com");
        request.setPassword("password");

        RegisterResponse response = userService.register(request);
        assertNotNull(response);
        assertTrue(response.getMessage().contains("successfully"));
    }

    @Test
    public void getUserTest() {
        GetUserRequest request = new GetUserRequest();
        request.setUserId(2L);

        GetUserResponse response = userService.getUserById(request);
        assertThat(response).isNotNull();
        assertThat(response.getEmail()).contains("test2");
    }

    @Test
    public void findAllUsersTest() {
        List<GetUserResponse> response = userService.findAll();
        System.out.println(response);
        assertThat(response).isNotNull();
        assertEquals(3, response.size());
    }
}
