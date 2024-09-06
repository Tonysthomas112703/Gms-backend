package com.example.GMS.repository;

import com.example.GMS.model.Grievance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrievanceRepository  extends JpaRepository <Grievance ,Long> {
}
