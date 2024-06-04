package com.maverickstube.maverickshub.dto.requests;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UploadMediaRequest {
    private MultipartFile mediaFile;
    private String description;
    private String resourceType;
}
