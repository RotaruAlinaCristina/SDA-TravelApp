package com.travelAppSpringBoot.controller;

import com.travelAppSpringBoot.model.Tour;
import com.travelAppSpringBoot.model.User;
import com.travelAppSpringBoot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<User> createNewTUser(@RequestBody User user) {
        User newUser = userService.createNewUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(newUser);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> readAllUser() {
        List<User> userList = userService.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PostMapping("/userEmail")
    public ResponseEntity<User> UserByEmail(String email) {
        User userByEmail = userService.findUserByEmail(email);
        return new ResponseEntity<>(userByEmail, HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.updateUser(user));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> readUserById(@PathVariable Long id) {
        User user = userService.findUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        Optional<User> userDeleted = userService.findUserById(id);
        if (userDeleted.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(userService.deleteUserById(id));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(userService.deleteUserById(id));
        }
    }
}
