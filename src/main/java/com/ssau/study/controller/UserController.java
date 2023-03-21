package com.ssau.study.controller;

import com.ssau.study.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsrenameInUpperCase() {
        return userService.getUsername().toUpperCase();
    }

    @GetMapping("/roles")
    public ArrayList<String> getMyRoles() {
        return userService.getRoles(userService.getUsername());
    }
}
