package com.maverickstube.maverickshub.services;

import com.github.fge.jsonpatch.JsonPatch;
import com.maverickstube.maverickshub.data.models.Media;
import com.maverickstube.maverickshub.dto.requests.UpdateMediaRequest;
import com.maverickstube.maverickshub.dto.requests.UploadMediaRequest;
import com.maverickstube.maverickshub.dto.responses.UpdateMediaResponse;
import com.maverickstube.maverickshub.dto.responses.UploadMediaResponse;

public interface MediaService {
    UploadMediaResponse upload(UploadMediaRequest request);

    Media findMediaBy(Long id);

    UpdateMediaResponse updateMedia(Long mediaId, UpdateMediaRequest request);

    UpdateMediaResponse updateMedia(Long mediaId, JsonPatch jsonPatch);
}
