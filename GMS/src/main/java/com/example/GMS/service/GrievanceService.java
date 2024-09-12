package com.example.GMS.service;

import com.example.GMS.exceptions.ResourceNotFoundException;
import com.example.GMS.exceptions.UnauthorizedActionException;
import com.example.GMS.model.Grievance;
import com.example.GMS.model.Technician;
import com.example.GMS.model.User;
import com.example.GMS.repository.GrievanceRepository;
import com.example.GMS.repository.TechnicianRepository;
import com.example.GMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;



@Service
public class GrievanceService {

    @Autowired
    private GrievanceRepository grievanceRepository;

    @Autowired
    private TechnicianRepository technicianRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserRepository userRepository;

    public Grievance createGrievance(Grievance grievance, String username, String password) {
        // Fetch user using Optional, or throw exception if not found
        Optional<User> optionalUser = userRepository.findByUsername(username);
        User user;

        if (optionalUser.isPresent()) {
            user = optionalUser.get(); // Extract user if present
        } else {
            // You can either create a new user here or throw an exception
            throw new RuntimeException("!!!!!User not found! Please register before filing a grievance.!!!!!!");
        }

        // Set the user to the grievance
        grievance.setUser(user);

        // Set the current date and time as the submission date
        grievance.setSubmittedDate(LocalDateTime.now());  // LocalDateTime includes both date and time

        // Set the initial status of the grievance
        grievance.setStatus("Submitted");

        // Save the grievance
        return grievanceRepository.save(grievance);
    }

    public Grievance getGrievanceById(Long id) {
        return grievanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("!!!Grievance not found!!!@"));
    }

    public Grievance assignTechnician(Long grievanceId, Long technicianId, String username, String password) {
        // Authenticate user and check role before proceeding
        if (!authenticationService.authenticate(username, password)) {
            throw new UnauthorizedActionException("!!!Invalid username or password.!!!");
        }

        if (!authenticationService.getRole(username).equals("ASSIGNEE")) {
            throw new UnauthorizedActionException("!!!Only Assignees can assign technicians.!!!");
        }

        // Fetch the grievance from the database
        Optional<Grievance> grievanceOpt = grievanceRepository.findById(grievanceId);
        if (!grievanceOpt.isPresent()) {
            throw new ResourceNotFoundException("!!!Grievance not found!!!");
        }

        // Fetch the technician from the database
        Optional<Technician> technicianOpt = technicianRepository.findById(technicianId);
        if (!technicianOpt.isPresent()) {
            throw new ResourceNotFoundException("!!!!Technician not found!!!");
        }

        // Assign the technician to the grievance
        Grievance grievance = grievanceOpt.get();
        Technician technician = technicianOpt.get();
        grievance.setTechnician(technician);

        // Save the grievance with the assigned technician
        return grievanceRepository.save(grievance);
    }


    public Grievance updateStatus(Long grievanceId, String status, String username, String password) {
        if (!authenticationService.authenticate(username, password)) {
            throw new UnauthorizedActionException("!!!Invalid username or password.!!!");
        }

        if (!authenticationService.getRole(username).equals("TECHNICIAN")) {
            throw new UnauthorizedActionException("!!!Only Technicians can update status of the grievance.!!!");
        }


        Grievance grievance = getGrievanceById(grievanceId);
        grievance.setStatus(status);
        grievance.setUpdatedDate(LocalDateTime.now());
        return grievanceRepository.save(grievance);

    }

}
