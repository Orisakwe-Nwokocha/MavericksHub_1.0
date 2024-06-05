package com.maverickstube.maverickshub.data.repositories;

import com.maverickstube.maverickshub.data.models.Media;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class MediaRepositoryTest {
    @Autowired
    private MediaRepository mediaRepository;

    @Test
    public void findAllMediaFor() {
        List<Media> media = mediaRepository.findAllMediaFor(200L);
        assertThat(media).hasSize(3);
    }
}