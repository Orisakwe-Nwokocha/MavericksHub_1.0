package com.maverickstube.maverickshub.dto.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterResponse {
    private String message;
    private Long id;
    private String email;
    @JsonProperty("created at")
    @JsonFormat(pattern = "dd/MMM/yyyy 'at' hh:mm:ss a")
    private LocalDateTime createdAt;
}
