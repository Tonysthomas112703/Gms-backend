package com.example.GMS.service;

import com.example.GMS.exceptions.ResourceNotFoundException;
import com.example.GMS.exceptions.UnauthorizedActionException;
import com.example.GMS.model.Grievance;
import com.example.GMS.model.Technician;
import com.example.GMS.repository.GrievanceRepository;
import com.example.GMS.repository.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GrievanceService {

    @Autowired
    private GrievanceRepository grievanceRepository;

    @Autowired
    private TechnicianRepository technicianRepository;

    @Autowired
    private AuthenticationService authenticationService;

    public Grievance createGrievance(Grievance grievance, String username, String password) {
        if (authenticationService.authenticate(username, password) && authenticationService.getUserRole(username).equals("USER")) {
            return grievanceRepository.save(grievance);
        } else {
            throw new UnauthorizedActionException("Only authenticated users can create grievances.");
        }
    }

    public Grievance getGrievanceById(Long id) {
        return grievanceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grievance not found"));
    }

    public Grievance assignTechnician(Long grievanceId, Technician technician, String username, String password) {
        Grievance grievance = getGrievanceById(grievanceId);
        if (authenticationService.authenticate(username, password) && authenticationService.getUserRole(username).equals("ASSIGNEE")) {
            grievance.setTechnician(technician);
            return grievanceRepository.save(grievance);
        } else {
            throw new UnauthorizedActionException("Only Assignees can assign technicians.");
        }
    }

    public Grievance updateStatus(Long grievanceId, String status, String username, String password) {
        Grievance grievance = getGrievanceById(grievanceId);
        if (authenticationService.authenticate(username, password) && authenticationService.getUserRole(username).equals("TECHNICIAN")) {
            grievance.setStatus(status);
            return grievanceRepository.save(grievance);
        } else {
            throw new UnauthorizedActionException("Only Technicians can update the status.");
        }
    }

    // Additional methods as needed...
}
