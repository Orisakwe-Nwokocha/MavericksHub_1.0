package com.maverickstube.maverickshub.dto.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetUserResponse {
    private Long id;
    private String email;
}
