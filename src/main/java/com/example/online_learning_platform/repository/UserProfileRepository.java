package com.example.online_learning_platform.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.online_learning_platform.domain.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {
    Boolean existsByUserId(UUID userId);

    Boolean existsByPhoneNumber(String phoneNumber);

}
