package com.maverickstube.maverickshub.services;

import com.maverickstube.maverickshub.dto.requests.UploadMediaRequest;
import com.maverickstube.maverickshub.dto.responses.UploadMediaResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MediaServiceTest {
    @Autowired
    private MediaService mediaService;

    @Test
    public void uploadMedia_uploadPictureTest() {
        String fileLocation = "C:\\Users\\DELL\\IdeaProjects\\maverickshub\\src\\main\\resources\\static\\arsenal.avif";
        UploadMediaRequest request = new UploadMediaRequest();
        Path path = Paths.get(fileLocation);
        try(var inputStream = Files.newInputStream(path)) {
            MultipartFile file = new MockMultipartFile("arsenal 2023 trophy", inputStream);
            request.setMediaFile(file);
            UploadMediaResponse response = mediaService.upload(request);

            assertThat(response).isNotNull();
            assertThat(response.getUrl()).isNotNull();
        } catch (IOException exception) {
            assertThat(exception).isNull();
        }
    }

    @Test
    public void uploadMedia_uploadVideoTest() {
        String fileLocation =
                "C:\\Users\\DELL\\IdeaProjects\\maverickshub\\src\\main\\resources\\static\\Uncle Ruckus.mp4";
        UploadMediaRequest request = new UploadMediaRequest();
        Path path = Paths.get(fileLocation);

        try(var inputStream = Files.newInputStream(path)) {
            MultipartFile file = new MockMultipartFile("boondocks uncle ruckus", inputStream);
            request.setMediaFile(file);
            request.setResourceType("video");
            UploadMediaResponse response = mediaService.upload(request);

            assertThat(response).isNotNull();
            assertThat(response.getUrl()).isNotNull();
        } catch (IOException exception) {
            assertThat(exception).isNull();
        }
    }

}