package com.example.online_learning_platform.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.online_learning_platform.domain.Location;
import com.example.online_learning_platform.domain.User;
import com.example.online_learning_platform.repository.LocationRepository;
import com.example.online_learning_platform.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;

    public String saveUser(User user, String locationId) {

        Boolean checkUser = userRepository.existsByEmail(user.getEmail());
        if (checkUser) {
            return "User with this email already exists.";
        }

        if (locationId != null) {
            Location location = locationRepository.findById(UUID.fromString(locationId)).orElse(null);
            if (location == null) {
                return "Location not found.";
            }
            user.setLocation(location);
        }

        userRepository.save(user);
        return "User saved successfully.";
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
            return users;
        } else {
            return null;
        }
    }

    public List<User> getUsersByFirstName(String firstName) {
        List<User> users = userRepository.findByFirstName(firstName);
        if (!users.isEmpty()) {
            return users;
        } else {
            return null;
        }
    }

    public List<User> getUsersByProvince(String provinceCode, String provinceName) {
        List<User> users = userRepository.findByProvinceCodeOrProvinceName(provinceCode, provinceName);
        if (!users.isEmpty()) {
            return users;
        } else {
            return null;
        }
    }

}
