package com.maverickstube.maverickshub.dto.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse {
    private String message;
    private Long id;
    private String email;
}
