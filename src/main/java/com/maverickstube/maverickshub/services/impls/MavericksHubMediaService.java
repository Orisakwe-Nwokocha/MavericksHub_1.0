package com.maverickstube.maverickshub.services.impls;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.maverickstube.maverickshub.data.models.Media;
import com.maverickstube.maverickshub.data.repositories.MediaRepository;
import com.maverickstube.maverickshub.dto.requests.UploadMediaRequest;
import com.maverickstube.maverickshub.dto.responses.UploadMediaResponse;
import com.maverickstube.maverickshub.exceptions.MediaUploadFailedException;
import com.maverickstube.maverickshub.services.MediaService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@AllArgsConstructor
public class MavericksHubMediaService implements MediaService {
    private final MediaRepository mediaRepository;
    private final Cloudinary cloudinary;
    private final ModelMapper modelMapper;

    @Override
    public UploadMediaResponse upload(UploadMediaRequest request) {
        try {
            Map<?, ?> response = cloudinary.uploader()
                    .upload(request.getMediaFile().getBytes(), ObjectUtils.emptyMap());
            String url = response.get("url").toString();
            Media media = modelMapper.map(request, Media.class);
            media.setUrl(url);
            Media savedMedia = mediaRepository.save(media);

            return modelMapper.map(savedMedia, UploadMediaResponse.class);
        } catch (IOException e) {
            throw new MediaUploadFailedException("Media upload failed");
        }
    }
}
