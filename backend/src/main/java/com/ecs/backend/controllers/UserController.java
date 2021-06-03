package com.ecs.backend.controllers;

import com.ecs.backend.model.User;
import com.ecs.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/users/")
public class UserController {

    final private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "auth/check-user")
    public ResponseEntity<?> checkUserCredentials(@RequestBody User user){
        return userService.checkUserCredentials(user);
    }
}
