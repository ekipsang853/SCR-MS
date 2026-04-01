package com.example.studentapp.service;

import com.example.studentapp.model.Course;
import com.example.studentapp.repository.CourseRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Integer id) {
        return courseRepository.findById(id).orElse(null);
    }

    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    public long countCourses() {
        return courseRepository.count();
    }
}
