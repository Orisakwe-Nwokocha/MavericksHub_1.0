package com.maverickstube.maverickshub.data.models;

import com.maverickstube.maverickshub.data.constants.Category;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static java.time.LocalDateTime.now;


@Entity
@Getter
@Setter
@ToString
public class Media {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String url;
    private String description;
    @Enumerated(STRING)
    private Category category;
    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Setter(AccessLevel.NONE)
    private LocalDateTime updatedAt;
    @ManyToOne
    private User uploader;

    @PrePersist
    private void setCreatedAt() {
        createdAt = now();
    }

    @PreUpdate
    private void setUpdatedAt() {
        updatedAt = now();
    }
}
