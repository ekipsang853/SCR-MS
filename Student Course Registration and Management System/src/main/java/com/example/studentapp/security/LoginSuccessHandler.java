package com.example.studentapp.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private static final DateTimeFormatter LOGIN_TIME_FORMAT =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication
    ) throws IOException, ServletException {
        HttpSession session = request.getSession(true);
        session.setAttribute("loginTime", LOGIN_TIME_FORMAT.format(LocalDateTime.now()));
        session.setAttribute("sessionId", session.getId());

        Object principal = authentication.getPrincipal();
        if (principal instanceof StudentUserDetails studentUserDetails) {
            session.setAttribute("studentId", studentUserDetails.getStudentId());
            session.setAttribute("studentName", studentUserDetails.getFullName());
            response.sendRedirect("/student/dashboard");
            return;
        }

        session.setAttribute("adminUsername", authentication.getName());
        response.sendRedirect("/");
    }
}
