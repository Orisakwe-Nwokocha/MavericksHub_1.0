package com.maverickstube.maverickshub.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maverickstube.maverickshub.data.constants.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateMediaResponse {
    @JsonProperty("media_id")
    private Long id;
    @JsonProperty("media_url")
    private String url;
    @JsonProperty("media_description")
    private String description;
    private Category category;
}
