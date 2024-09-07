package com.example.GMS.service;

import com.example.GMS.model.User;
import com.example.GMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service


public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        // Optionally, you can add validation, password encryption, etc.
        return userRepository.save(user);
    }
}


