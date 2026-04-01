package com.example.studentapp.service;

import com.example.studentapp.model.Student;
import com.example.studentapp.repository.StudentRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentService(StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void saveStudent(Student student) {
        if (student.getId() != null) {
            Student existingStudent = getStudentById(student.getId());
            if (existingStudent != null && !StringUtils.hasText(student.getPassword())) {
                student.setPassword(existingStudent.getPassword());
            }
        }

        if (StringUtils.hasText(student.getPassword())
            && !student.getPassword().startsWith("$2a$")
            && !student.getPassword().startsWith("$2b$")
            && !student.getPassword().startsWith("$2y$")) {
            student.setPassword(passwordEncoder.encode(student.getPassword()));
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

    public long countStudents() {
        return studentRepository.count();
    }

    public Optional<Student> findByEmail(String email) {
        return studentRepository.findByEmailIgnoreCase(email);
    }
}
