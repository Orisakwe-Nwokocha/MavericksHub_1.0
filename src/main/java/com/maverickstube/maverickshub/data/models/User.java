package com.maverickstube.maverickshub.data.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;
import static java.time.LocalDateTime.now;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Setter(AccessLevel.NONE)
    private LocalDateTime updatedAt;
    @OneToMany
    private List<Media> media;

    @PrePersist
    private void setCreatedAt() {
        createdAt = now();
    }

    @PreUpdate
    private void setUpdatedAt() {
        updatedAt = now();
    }

    public static void main(String[] args) {
        System.out.println(now());
    }

}
