package com.example.GMS.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Grievance {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long grievanceId;

        private String type; // e.g., Service-based, Product-based
        private String description;
        private String status; // e.g., Submitted, Assigned, In Progress, Resolved

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technician_Id")
    @JsonIgnore
    private Technician technician;

        private LocalDateTime submittedDate;
        private LocalDateTime updatedDate;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Technician getTechnician() {
            return technician;
        }

        public void setTechnician(Technician technician) {
            this.technician = technician;
        }

        public LocalDateTime getSubmittedDate() {
            return submittedDate;
        }

        public void setSubmittedDate(LocalDateTime submittedDate) {
            this.submittedDate = submittedDate;
        }

        public LocalDateTime getUpdatedDate() {
            return updatedDate;
        }

        public void setUpdatedDate(LocalDateTime updatedDate) {
            this.updatedDate = updatedDate;
        }

        // Getters and Setters
    }

