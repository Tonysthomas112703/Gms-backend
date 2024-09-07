package com.example.GMS.controller;

import com.example.GMS.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        boolean authenticated = authenticationService.authenticate(username, password);
            return "Login successful! Role: " + authenticationService.getRole(username);
    }
}
