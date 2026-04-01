package com.example.studentapp.repository;

import com.example.studentapp.model.Registration;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
    List<Registration> findByStudentId(Integer studentId);

    long countByStudentId(Integer studentId);
}
