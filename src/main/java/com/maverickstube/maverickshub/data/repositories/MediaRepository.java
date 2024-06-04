package com.maverickstube.maverickshub.data.repositories;

import com.maverickstube.maverickshub.data.models.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {
}
