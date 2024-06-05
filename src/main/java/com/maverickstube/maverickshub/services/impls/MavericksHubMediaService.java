package com.maverickstube.maverickshub.services.impls;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.maverickstube.maverickshub.data.models.Media;
import com.maverickstube.maverickshub.data.models.User;
import com.maverickstube.maverickshub.data.repositories.MediaRepository;
import com.maverickstube.maverickshub.dto.requests.UpdateMediaRequest;
import com.maverickstube.maverickshub.dto.requests.UploadMediaRequest;
import com.maverickstube.maverickshub.dto.responses.MediaResponse;
import com.maverickstube.maverickshub.dto.responses.UpdateMediaResponse;
import com.maverickstube.maverickshub.dto.responses.UploadMediaResponse;
import com.maverickstube.maverickshub.exceptions.MediaNotFoundException;
import com.maverickstube.maverickshub.exceptions.MediaUpdateFailedException;
import com.maverickstube.maverickshub.exceptions.MediaUploadFailedException;
import com.maverickstube.maverickshub.services.MediaService;
import com.maverickstube.maverickshub.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class MavericksHubMediaService implements MediaService {
    private final MediaRepository mediaRepository;
    private final Cloudinary cloudinary;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Override
    public UploadMediaResponse upload(UploadMediaRequest request) {
        User user = userService.findUserBy(request.getUserId());
        try {
            var map = ObjectUtils.asMap(
                    "resource_type", "auto",
                    "overwrite", true,
                    "use_filename", true,
                    "unique_filename", false
            );

            Map<?, ?> response = cloudinary.uploader()
                    .upload(request.getMediaFile().getBytes(), map);
            String url = response.get("url").toString();
            Media media = modelMapper.map(request, Media.class);
            media.setUrl(url);
            media.setUploader(user);
            Media savedMedia = mediaRepository.save(media);

            return modelMapper.map(savedMedia, UploadMediaResponse.class);
        } catch (IOException e) {
            throw new MediaUploadFailedException("Media upload failed");
        }
    }

    @Override
    public Media findMediaBy(Long id) {
        return mediaRepository.findById(id).orElseThrow(() -> new MediaNotFoundException(
                String.format("Media with id %s not found", id)));
    }

    @Override
    public UpdateMediaResponse updateMedia(Long mediaId, UpdateMediaRequest request) {
        Media media = findMediaBy(mediaId);
        modelMapper.map(request, media);
        Media savedMedia = mediaRepository.save(media);
        return modelMapper.map(savedMedia, UpdateMediaResponse.class);
    }

    @Override
    public UpdateMediaResponse updateMedia(Long mediaId, JsonPatch jsonPatch) {
        try {
            Media media = findMediaBy(mediaId);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode mediaNode = objectMapper.convertValue(media, JsonNode.class);
            mediaNode = jsonPatch.apply(mediaNode);
            media = objectMapper.convertValue(mediaNode, Media.class);
            media = mediaRepository.save(media);
            return modelMapper.map(media, UpdateMediaResponse.class);
        } catch (JsonPatchException e) {
            throw new MediaUpdateFailedException(e.getMessage());
        }
    }

    @Override
    public List<MediaResponse> getMediaFor(Long userId) {
        List<Media> media = mediaRepository.findAllMediaFor(userId);
        return List.of(modelMapper.map(media, MediaResponse[].class));
    }
}
