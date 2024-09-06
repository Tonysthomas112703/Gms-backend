package com.example.GMS.service;

import com.example.GMS.model.User;
import com.example.GMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    // Method to authenticate user based on username and password from database
    public boolean authenticate(String username, String password) {
        // Find user by username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the password matches (you can also hash the passwords and use BCrypt for comparison)
        return user.getPassword().equals(password);
    }

    // Additional method to get the user's role
    public String getUserRole(String role) {
        User user = userRepository.findByUsername(role)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getRole();
    }
}
