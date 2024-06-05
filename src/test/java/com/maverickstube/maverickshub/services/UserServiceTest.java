package com.maverickstube.maverickshub.services;

import com.maverickstube.maverickshub.data.models.User;
import com.maverickstube.maverickshub.dto.requests.GetUserRequest;
import com.maverickstube.maverickshub.dto.requests.RegisterRequest;
import com.maverickstube.maverickshub.dto.responses.GetUserResponse;
import com.maverickstube.maverickshub.dto.responses.RegisterResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(scripts = {"/db/data.sql"})
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
        request.setUserId(200L);

        GetUserResponse response = userService.getUserById(request);
        assertThat(response).isNotNull();
        assertThat(response.getEmail()).contains("test");
    }

    @Test
    public void findAllUsersTest() {
        List<GetUserResponse> response = userService.findAllUsers();
        System.out.println(response);
        assertThat(response).isNotNull();
        assertThat( response.size()).isGreaterThanOrEqualTo(3);
    }

    @Test
    @DisplayName("test that user can be retrieved by id")
    public void getUserByIdTest() {
        Long id = 200L;
        User user = userService.findUserBy(id);
        assertThat(user.getId()).isNotNull();
    }
}
