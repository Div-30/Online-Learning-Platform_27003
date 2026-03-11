package com.example.online_learning_platform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.online_learning_platform.domain.User;
import com.example.online_learning_platform.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUser(@RequestBody User user, @RequestParam(required = false) String locationId) {

        String response = userService.saveUser(user, locationId);

        if (response.equals("User saved successfully.")) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUsers() {

        List<User> users = userService.getAllUsers();

        if (users != null) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No users found.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/searchByFirstName", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUsersByFirstName(@RequestParam String firstName) {

        List<User> users = userService.getUsersByFirstName(firstName);

        if (users != null) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No users found with the provided first name.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/searchByProvince", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUsersByProvince(
            @RequestParam(required = false) String provinceCode,
            @RequestParam(required = false) String provinceName) {

        List<User> users = userService.getUsersByProvince(provinceCode, provinceName);

        if (users != null) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No users found in the provided province.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/searchByDistrict", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUsersByDistrict(
            @RequestParam(required = false) String districtCode,
            @RequestParam(required = false) String districtName) {

        List<User> users = userService.getUsersByDistrict(districtCode, districtName);

        if (users != null) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No users found in the provided district.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/searchBySector", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUsersBySector(
            @RequestParam(required = false) String sectorCode,
            @RequestParam(required = false) String sectorName) {

        List<User> users = userService.getUsersBySector(sectorCode, sectorName);

        if (users != null) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No users found in the provided sector.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/searchByCell", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUsersByCell(
            @RequestParam(required = false) String cellCode,
            @RequestParam(required = false) String cellName) {

        List<User> users = userService.getUsersByCell(cellCode, cellName);

        if (users != null) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No users found in the provided cell.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/searchByVillage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUsersByVillage(
            @RequestParam(required = false) String villageCode,
            @RequestParam(required = false) String villageName) {

        List<User> users = userService.getUsersByVillage(villageCode, villageName);

        if (users != null) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No users found in the provided village.", HttpStatus.NOT_FOUND);
        }
    }

}
