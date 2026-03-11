package com.example.online_learning_platform.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.online_learning_platform.domain.User;
import com.example.online_learning_platform.domain.UserProfile;
import com.example.online_learning_platform.repository.UserProfileRepository;
import com.example.online_learning_platform.repository.UserRepository;

@Service
public class UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserRepository userRepository;

    public String saveUserProfile(UserProfile userProfile, String userId) {

        User user = userRepository.findById(UUID.fromString(userId)).orElse(null);
        if (user == null) {
            return "User not found.";
        }

        Boolean checkExistingProfile = userProfileRepository.existsByUserId(UUID.fromString(userId));
        if (checkExistingProfile) {
            return "This user already has a profile.";
        }

        Boolean checkPhone = userProfileRepository.existsByPhoneNumber(userProfile.getPhoneNumber());
        if (checkPhone) {
            return "A profile with this phone number already exists.";
        }

        userProfile.setUser(user);
        userProfileRepository.save(userProfile);
        return "User profile saved successfully.";
    }
}