package com.maverickstube.maverickshub.dto.requests;

import com.maverickstube.maverickshub.data.constants.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMediaRequest {
    private Long mediaId;
    private String url;
    private String description;
    private Category category;
}
