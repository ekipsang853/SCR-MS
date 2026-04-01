package com.example.studentapp.service;

import com.example.studentapp.model.Student;
import com.example.studentapp.security.StudentUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final StudentService studentService;
    private final PasswordEncoder passwordEncoder;
    private final String adminUsername;
    private final String adminPassword;

    public CustomUserDetailsService(
        StudentService studentService,
        PasswordEncoder passwordEncoder,
        @Value("${spring.security.user.name:admin}") String adminUsername,
        @Value("${spring.security.user.password:1234}") String adminPassword
    ) {
        this.studentService = studentService;
        this.passwordEncoder = passwordEncoder;
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (adminUsername.equalsIgnoreCase(username)) {
            return User.withUsername(adminUsername)
                .password(passwordEncoder.encode(adminPassword))
                .roles("ADMIN")
                .build();
        }

        Student student = studentService.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (student.getPassword() == null || student.getPassword().isBlank()) {
            throw new UsernameNotFoundException("Student login not configured");
        }

        return new StudentUserDetails(student);
    }
}
