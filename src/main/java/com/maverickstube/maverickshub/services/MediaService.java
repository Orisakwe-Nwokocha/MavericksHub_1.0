package com.maverickstube.maverickshub.services;

import com.maverickstube.maverickshub.dto.requests.UploadMediaRequest;
import com.maverickstube.maverickshub.dto.responses.UploadMediaResponse;

public interface MediaService {
//    UploadMediaResponse upload(UploadMediaRequest request);
    UploadMediaResponse upload(UploadMediaRequest request, String resourceType);
}
