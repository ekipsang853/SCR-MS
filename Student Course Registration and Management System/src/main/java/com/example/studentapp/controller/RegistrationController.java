package com.example.studentapp.controller;

import com.example.studentapp.model.Course;
import com.example.studentapp.model.Registration;
import com.example.studentapp.model.Student;
import com.example.studentapp.service.CourseService;
import com.example.studentapp.service.RegistrationService;
import com.example.studentapp.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final StudentService studentService;
    private final CourseService courseService;

    public RegistrationController(
        RegistrationService registrationService,
        StudentService studentService,
        CourseService courseService
    ) {
        this.registrationService = registrationService;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping
    public String viewRegistrations(Model model) {
        model.addAttribute("registrations", registrationService.getAllRegistrations());
        return "registrations";
    }

    @GetMapping("/new")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registration", new Registration());
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("courses", courseService.getAllCourses());
        return "register-course";
    }

    @PostMapping("/save")
    public String saveRegistration(
        @RequestParam Integer studentId,
        @RequestParam Integer courseId,
        @RequestParam String semester
    ) {
        Student student = studentService.getStudentById(studentId);
        Course course = courseService.getCourseById(courseId);

        Registration registration = new Registration(student, course, semester);
        registrationService.registerCourse(registration);

        return "redirect:/registrations";
    }

    @GetMapping("/delete/{id}")
    public String deleteRegistration(@PathVariable Integer id) {
        registrationService.dropCourse(id);
        return "redirect:/registrations";
    }
}
