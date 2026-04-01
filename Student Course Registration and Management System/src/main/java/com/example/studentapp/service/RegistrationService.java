package com.example.studentapp.service;

import com.example.studentapp.model.Registration;
import com.example.studentapp.repository.RegistrationRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public void registerCourse(Registration registration) {
        registrationRepository.save(registration);
    }

    public void dropCourse(Integer id) {
        registrationRepository.deleteById(id);
    }

    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    public long countRegistrations() {
        return registrationRepository.count();
    }

    public List<Registration> getRegistrationsForStudent(Integer studentId) {
        return registrationRepository.findByStudentId(studentId);
    }

    public long countRegistrationsForStudent(Integer studentId) {
        return registrationRepository.countByStudentId(studentId);
    }
}
