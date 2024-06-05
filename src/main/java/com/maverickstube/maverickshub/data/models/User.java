package com.maverickstube.maverickshub.data.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;
    @Setter(AccessLevel.NONE)
    private LocalDateTime updatedAt;

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
