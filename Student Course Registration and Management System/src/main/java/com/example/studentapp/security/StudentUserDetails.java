package com.example.studentapp.security;

import com.example.studentapp.model.Student;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class StudentUserDetails implements UserDetails {

    private final Integer studentId;
    private final String fullName;
    private final String username;
    private final String password;

    public StudentUserDetails(Student student) {
        this.studentId = student.getId();
        this.fullName = student.getName();
        this.username = student.getEmail();
        this.password = student.getPassword();
    }

    public Integer getStudentId() {
        return studentId;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_STUDENT"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
