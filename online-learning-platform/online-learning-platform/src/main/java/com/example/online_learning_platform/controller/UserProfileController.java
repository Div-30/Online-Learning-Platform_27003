package com.example.online_learning_platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.online_learning_platform.domain.UserProfile;
import com.example.online_learning_platform.service.UserProfileService;

@RestController
@RequestMapping("/api/Profiles")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUserProfile(@RequestBody UserProfile userProfile, @RequestParam String userId) {

        String response = userProfileService.saveUserProfile(userProfile, userId);

        if (response.equals("User profile saved successfully.")) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }
}
