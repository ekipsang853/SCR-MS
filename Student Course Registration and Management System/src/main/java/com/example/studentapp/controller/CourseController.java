package com.example.studentapp.controller;

import com.example.studentapp.model.Course;
import com.example.studentapp.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "courses";
    }

    @GetMapping("/new")
    public String showCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "add-course";
    }

    @GetMapping("/edit/{id}")
    public String editCourse(@PathVariable Integer id, Model model) {
        model.addAttribute("course", courseService.getCourseById(id));
        return "add-course";
    }

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute Course course) {
        courseService.saveCourse(course);
        return "redirect:/courses";
    }
}
