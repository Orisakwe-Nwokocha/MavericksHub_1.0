package com.maverickstube.maverickshub.services.impls;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.maverickstube.maverickshub.config.CloudConfig;
import com.maverickstube.maverickshub.data.models.Media;
import com.maverickstube.maverickshub.data.repositories.MediaRepository;
import com.maverickstube.maverickshub.dto.requests.UploadMediaRequest;
import com.maverickstube.maverickshub.dto.responses.UploadMediaResponse;
import com.maverickstube.maverickshub.exceptions.MediaUploadFailedException;
import com.maverickstube.maverickshub.services.MediaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class MavericksHubMediaService implements MediaService {
    private final MediaRepository mediaRepository;
    private final Cloudinary cloudinary;

    @Override
    public UploadMediaResponse upload(UploadMediaRequest request) {
        try {
            Map<?, ?> response = cloudinary.uploader()
                    .upload(request.getMediaFile().getBytes(), null);
            log.info("Cloudinary upload response:: {}", response);
            String url = response.get("url").toString();

            Media media = new Media();
            media.setUrl(url);
            media.setDescription(request.getDescription());
            Media savedMedia = mediaRepository.save(media);

            UploadMediaResponse uploadMediaResponse = new UploadMediaResponse();
            uploadMediaResponse.setMediaUrl(url);
            uploadMediaResponse.setMediaId(savedMedia.getId());
            uploadMediaResponse.setDescription(savedMedia.getDescription());
            return uploadMediaResponse;
        } catch (IOException e) {
            throw new MediaUploadFailedException("Media upload failed");
        }
    }
}
