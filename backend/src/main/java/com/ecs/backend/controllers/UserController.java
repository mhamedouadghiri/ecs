package com.ecs.backend.controllers;

import com.ecs.backend.dto.UserDto;
import com.ecs.backend.model.User;
import com.ecs.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/auth/check-user")
    public ResponseEntity<?> checkUserCredentials(@RequestBody User user) {
        return userService.checkUserCredentials(user);
    }

    @PostMapping(path = "/auth/register-user")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        return userService.registerUser(userDto);
    }
}
