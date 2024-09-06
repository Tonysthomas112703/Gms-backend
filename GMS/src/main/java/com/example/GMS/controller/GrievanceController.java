package com.example.GMS.controller;

import com.example.GMS.model.Grievance;
import com.example.GMS.model.Technician;
import com.example.GMS.service.GrievanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grievances")
public class GrievanceController {

    @Autowired
    private GrievanceService grievanceService;

    @PostMapping
    public Grievance createGrievance(@RequestBody Grievance grievance,
                                     @RequestParam String username,
                                     @RequestParam String password) {
        return grievanceService.createGrievance(grievance, username, password);
    }

    @PutMapping("/{id}/assign")
    public Grievance assignTechnician(@PathVariable Long id,
                                      @RequestBody Technician technician,
                                      @RequestParam String username,
                                      @RequestParam String password) {
        return grievanceService.assignTechnician(id, technician, username, password);
    }

    @PutMapping("/{id}/status")
    public Grievance updateStatus(@PathVariable Long id,
                                  @RequestParam String status,
                                  @RequestParam String username,
                                  @RequestParam String password) {
        return grievanceService.updateStatus(id, status, username, password);
    }

    @GetMapping("/{id}")
    public Grievance getGrievance(@PathVariable Long id) {
        return grievanceService.getGrievanceById(id);
    }
}
