package com.example.GMS.controller;

import com.example.GMS.exceptions.ResourceNotFoundException;
import com.example.GMS.exceptions.UnauthorizedActionException;
import com.example.GMS.model.Grievance;
import com.example.GMS.service.GrievanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/grievances")
public class GrievanceController {

    @Autowired
    private GrievanceService grievanceService;

    @PostMapping
    public Grievance createGrievance(@RequestBody Grievance grievance,
                                     @RequestParam String username,
                                     @RequestParam String password) {
        return grievanceService.createGrievance(grievance,username,password);
    }


    @PutMapping("/{grievanceId}/assign")
    public String assignTechnician(
            @PathVariable Long grievanceId,
            @RequestParam Long technicianId,
            @RequestParam String username,
            @RequestParam String password) {
        Grievance grievance = grievanceService.assignTechnician(grievanceId, technicianId, username, password);
        return "Technician Has Being Assigned!!!" +ResponseEntity.ok(grievance);
    }

    @ExceptionHandler(UnauthorizedActionException.class)
    public ResponseEntity<String> handleUnauthorized(UnauthorizedActionException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }


    @PutMapping("/{grievance_Id}/update-status")
    public String updateStatus(@PathVariable Long grievance_Id,
                               @RequestParam String status,
                               @RequestParam String username,
                               @RequestParam String password) {
        return  "Grievance Status is updated Successfully!!!!" + grievanceService.updateStatus(grievance_Id, status, username, password);


    }



    @GetMapping("/{grievance_Id}")
    public ResponseEntity<Grievance> getGrievanceById(@PathVariable Long grievance_Id) {
        Grievance grievance = grievanceService.getGrievanceById(grievance_Id);
        return new ResponseEntity<>(grievance, HttpStatus.OK);
    }
}
