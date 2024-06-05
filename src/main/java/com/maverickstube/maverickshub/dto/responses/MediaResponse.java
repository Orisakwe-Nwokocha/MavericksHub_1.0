package com.maverickstube.maverickshub.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.maverickstube.maverickshub.data.constants.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class MediaResponse {
    @JsonProperty("media_id")
    private Long id;
    @JsonProperty("media_url")
    private String url;
    @JsonProperty("media_description")
    private String description;
    private Category category;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updatedAt;
    private UserResponse uploader;
}
