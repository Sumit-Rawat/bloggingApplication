package com.blog.blog_app_apis.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

//using @Component because we need to inject instance using @Autowired
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    // jb koi unauthorized user access karna chahega 'authorized' api ko
    // tb ye function call hoga.
    // error bhejenge is method ke through sb parameter ke help se.
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied !!");

    }

}
