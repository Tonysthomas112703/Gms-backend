package com.example.GMS20.Controller;

import com.example.GMS20.Services.GrievanceService;
import com.example.GMS20.model.Grievance;
import com.example.GMS20.model.Technician;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grievance")
public class GrievanceController {

    @Autowired
    private GrievanceService grievanceService;

//creating grievances
    @PostMapping("/create")
    public ResponseEntity<Grievance> createGrievance(@RequestBody Grievance grievance) {
        Grievance createdGrievance = grievanceService.createGrievance(grievance);
        return new ResponseEntity<>(createdGrievance, HttpStatus.CREATED);
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<List<Grievance>> getGrievancesByUsername(@PathVariable String username) {
        List<Grievance> grievances = grievanceService.getGrievancesByUsername(username);
        if (grievances.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(grievances, HttpStatus.OK);
    }


    @GetMapping("/unassigned")
    public ResponseEntity<List<Grievance>> getUnassignedGrievances() {
        List<Grievance> grievances = grievanceService.getUnassignedGrievances();
        if (grievances.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(grievances, HttpStatus.OK);
    }

    // View all technicians (for Assignee)
    @GetMapping("/technicians")
    public ResponseEntity<List<Technician>> getAllTechnicians() {
        List<Technician> technicians = grievanceService.getAllTechnicians();
        return ResponseEntity.ok(technicians);
    }

    // Assign a technician to a grievance (for Assignee)
    @PatchMapping("/assign/{grievanceId}/{technicianId}")
    public ResponseEntity<String> assignTechnician(@PathVariable Long grievanceId, @PathVariable Long technicianId) {
        Technician technician = grievanceService.assignTechnicianToGrievance(grievanceId, technicianId);
        return ResponseEntity.ok("Technician " + technician.getUsername() + " assigned successfully");
    }


    @GetMapping("/get-grievances/{technicianId}")
    public ResponseEntity<List<Grievance>> getGrievancesByTechnicianId(@PathVariable Long technicianId) {
        List<Grievance> grievances = grievanceService.getGrievancesByTechnicianId(technicianId);
        if (grievances.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(grievances, HttpStatus.OK);
    }

    // Update the status of a grievance (for Technician)
    @PatchMapping("/update-grievance-status/{grievanceId}")
    public ResponseEntity<String> updateGrievanceStatus(@PathVariable Long grievanceId, @RequestParam String status) {
        Grievance grievance = grievanceService.updateGrievanceStatus(grievanceId, status);
        return ResponseEntity.ok("Grievance status updated successfully to " + grievance.getStatus());
    }

    // Update technician's own status (for Technician)
    @PatchMapping("/update-technician-status/{technicianId}")
    public ResponseEntity<String> updateTechnicianStatus(@PathVariable Long technicianId, @RequestParam String status) {
        Technician technician = grievanceService.updateTechnicianStatus(technicianId, status);
        return ResponseEntity.ok("Technician " + technician.getUsername() + "'s status updated to " + status);
    }
}

