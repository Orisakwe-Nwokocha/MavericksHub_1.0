package com.maverickstube.maverickshub.services;

import com.fasterxml.jackson.databind.node.TextNode;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchOperation;
import com.github.fge.jsonpatch.ReplaceOperation;
import com.maverickstube.maverickshub.data.models.Media;
import com.maverickstube.maverickshub.dto.requests.UpdateMediaRequest;
import com.maverickstube.maverickshub.dto.requests.UploadMediaRequest;
import com.maverickstube.maverickshub.dto.responses.UpdateMediaResponse;
import com.maverickstube.maverickshub.dto.responses.UploadMediaResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.maverickstube.maverickshub.data.constants.Category.*;
import static com.maverickstube.maverickshub.utils.TestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Sql(scripts = {"/db/data.sql"})
public class MediaServiceTest {
    @Autowired
    private MediaService mediaService;

    @Test
    public void uploadMedia_uploadPictureTest() {
        Path path = Paths.get(TEST_IMAGE_LOCATION);
        try(var inputStream = Files.newInputStream(path)) {
            UploadMediaRequest request = buildUploadMediaRequest(inputStream);
            UploadMediaResponse response = mediaService.upload(request);
            assertThat(response).isNotNull();
            assertThat(response.getUrl()).isNotNull();
        } catch (IOException exception) {
            assertThat(exception).isNull();
        }
    }

    @Test
    public void uploadMedia_uploadVideoTest() {
        Path path = Paths.get(TEST_VIDEO_LOCATION);
        try(var inputStream = Files.newInputStream(path)) {
            UploadMediaRequest request = buildUploadMediaRequest(inputStream);
            UploadMediaResponse response = mediaService.upload(request);
            log.info("response ==> {}", response);
            assertThat(response).isNotNull();
            assertThat(response.getUrl()).isNotNull();
        } catch (IOException exception) {
            assertThat(exception).isNull();
        }
    }

    @Test
    @DisplayName("test that media can be retrieved by id")
    public void getMediaByIdTest() {
        Long id = 101L;
        Media media = mediaService.findMediaBy(id);
        log.info("media ==> {}", media);
        assertThat(media.getId()).isNotNull();
    }

    @Test
    @DisplayName("given action category, when updated, then category is step_mum")
    public void uploadMediaTest() {
        Long id = 100L;
        Media media = mediaService.findMediaBy(id);
        assertThat(media.getCategory()).isEqualTo(ACTION);

        UpdateMediaRequest updateMediaRequest = new UpdateMediaRequest();
        updateMediaRequest.setCategory(STEP_MUM);
        var response = mediaService.updateMedia(id, updateMediaRequest);

        log(response);
        assertThat(response).isNotNull();
        media = mediaService.findMediaBy(id);
        assertThat(media.getCategory()).isEqualTo(STEP_MUM);
    }

    private static void log(UpdateMediaResponse response) {
        log.info("response: {}", response);
    }

    @Test
    @DisplayName("given action category, when updated, then category is step_mum")
    public void uploadMediaUsingJsonPatchTest() throws JsonPointerException {
        Long id = 100L;
        Media media = mediaService.findMediaBy(id);
        assertThat(media.getCategory()).isEqualTo(ACTION);

        List<JsonPatchOperation> operations = List.of(
                new ReplaceOperation(new JsonPointer("/category"),
                        new TextNode(STEP_MUM.name()))
        );
        JsonPatch jsonPatch = new JsonPatch(operations);
        var response = mediaService.updateMedia(id, jsonPatch);

        log(response);
        assertThat(response).isNotNull();
        media = mediaService.findMediaBy(id);
        assertThat(media.getCategory()).isEqualTo(STEP_MUM);
    }

}