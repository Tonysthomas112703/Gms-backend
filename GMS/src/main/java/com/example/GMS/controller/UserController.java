package com.example.GMS.controller;

import com.example.GMS.model.User;
import com.example.GMS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        return "!!!!!!Successfully registered!!!!!!" +  userService.registerUser(user);

    }
}
