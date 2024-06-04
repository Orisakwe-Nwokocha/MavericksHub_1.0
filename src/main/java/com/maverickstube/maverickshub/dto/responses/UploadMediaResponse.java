package com.maverickstube.maverickshub.dto.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadMediaResponse {
    private Long id;
    private String url;
    private String description;
}
