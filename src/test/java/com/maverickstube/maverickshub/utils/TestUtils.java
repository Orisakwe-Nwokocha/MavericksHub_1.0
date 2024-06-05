package com.maverickstube.maverickshub.utils;

import com.maverickstube.maverickshub.dto.requests.UploadMediaRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static com.maverickstube.maverickshub.data.constants.Category.COMEDY;

public class TestUtils {
    public static final String TEST_IMAGE_LOCATION = "C:\\Users\\DELL\\IdeaProjects\\maverickshub\\src\\main\\resources\\static\\arsenal.avif";
    public static final String TEST_VIDEO_LOCATION = "C:\\Users\\DELL\\IdeaProjects\\maverickshub\\src\\main\\resources\\static\\Uncle Ruckus.mp4";


    public static UploadMediaRequest buildUploadMediaRequest(InputStream inputStream) throws IOException {
        UploadMediaRequest request = new UploadMediaRequest();
        MultipartFile file = new MockMultipartFile("media", inputStream);
        request.setMediaFile(file);
        request.setUserId(200L);
        request.setCategory(COMEDY);
        return request;
    }
}
