package com.example.GMS.repository;

import com.example.GMS.model.Technician;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicianRepository extends JpaRepository<Technician,Long> {
}
