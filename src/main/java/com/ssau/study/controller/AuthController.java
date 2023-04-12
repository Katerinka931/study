package com.ssau.study.controller;


import com.ssau.study.dto.UserPojo;
import com.ssau.study.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public UserPojo login(@RequestBody UserPojo pojo) {
        return authService.login(pojo);
    }

    @PostMapping("/register")
    public UserPojo register(@RequestBody UserPojo pojo) {
        return authService.register(pojo);
    }
}


