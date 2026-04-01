package com.example.studentapp.controller;

import com.example.studentapp.model.Registration;
import com.example.studentapp.model.Student;
import com.example.studentapp.security.StudentUserDetails;
import com.example.studentapp.service.RegistrationService;
import com.example.studentapp.service.StudentService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentPortalController {

    private final StudentService studentService;
    private final RegistrationService registrationService;

    public StudentPortalController(StudentService studentService, RegistrationService registrationService) {
        this.studentService = studentService;
        this.registrationService = registrationService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, HttpSession session, Model model) {
        Student student = getLoggedInStudent(authentication);
        List<Registration> registrations = registrationService.getRegistrationsForStudent(student.getId());

        model.addAttribute("student", student);
        model.addAttribute("registrationCount", registrations.size());
        model.addAttribute("registrations", registrations);
        return "student-dashboard";
    }

    @GetMapping("/courses")
    public String courses(Authentication authentication, HttpSession session, Model model) {
        Student student = getLoggedInStudent(authentication);

        model.addAttribute("student", student);
        model.addAttribute("registrations", registrationService.getRegistrationsForStudent(student.getId()));
        model.addAttribute("sessionId", session.getAttribute("sessionId"));
        model.addAttribute("loginTime", session.getAttribute("loginTime"));
        return "student-courses";
    }

    private Student getLoggedInStudent(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof StudentUserDetails studentUserDetails)) {
            throw new IllegalStateException("Authenticated user is not a student");
        }

        Student student = studentService.getStudentById(studentUserDetails.getStudentId());
        if (student == null) {
            throw new IllegalStateException("Student account not found");
        }

        return student;
    }
}
