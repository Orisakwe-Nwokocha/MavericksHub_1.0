package com.maverickstube.maverickshub.dto.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maverickstube.maverickshub.data.constants.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MediaResponse {
    @JsonProperty("media_id")
    private Long id;

    @JsonProperty("media_url")
    private String url;

    @JsonProperty("media_description")
    private String description;

    private Category category;

    @JsonFormat(pattern = "dd/MMM/yyyy 'at' hh:mm:ss a")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd/MMM/yyyy 'at' hh:mm:ss a")
    private LocalDateTime updatedAt;

    private UserResponse uploader;
}
