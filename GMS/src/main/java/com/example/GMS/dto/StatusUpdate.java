package com.example.GMS.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class StatusUpdate {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getters and Setters
}
