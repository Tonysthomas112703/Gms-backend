package com.example.GMS.controller;

import com.example.GMS.exceptions.UnauthorizedActionException;
import com.example.GMS.model.Grievance;
import com.example.GMS.repository.GrievanceRepository;
import com.example.GMS.service.AuthenticationService;
import com.example.GMS.service.GrievanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private GrievanceService grievanceService;

    @PostMapping("/login")
    public String createGrievance(@RequestBody Grievance grievance,
                                  @RequestHeader("username") String username,
                                  @RequestHeader("password") String password) {
        if (authenticationService.authenticate(username, password)) {
            return "Grievance created: ";
        } else {
            throw new UnauthorizedActionException("Invalid credentials");
        }
    }


//    @PostMapping("/login")
//    public String login(@RequestParam String username, @RequestParam String password) {
//        boolean authenticated = authenticationService.authenticate(username, password);
//        if (authenticated) {
//            return "Login successful! Role: " + authenticationService.getRole(username);
//        } else {
//            return "Login failed: Invalid credentials";
//        }
//    }
}
