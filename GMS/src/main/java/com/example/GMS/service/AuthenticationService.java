package com.example.GMS.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    private static final Map<String, String> users = new HashMap<>();

    static {
        // In a real application, you would load users from a database
       // users.put("user", "password"); // USER role
        users.put("assignee", "assigneepassword"); // ASSIGNEE role
        users.put("technician", "technicianpassword"); // TECHNICIAN role
    }

    public boolean authenticate(String username, String password) {
        return password.equals(users.get(username));
    }

    public String getRole(String username) {
        //if (username.equals("user")) return "USER";
        if (username.equals("assignee")) return "ASSIGNEE";
        if (username.equals("technician")) return "TECHNICIAN";
        return "GUEST";
    }
}
