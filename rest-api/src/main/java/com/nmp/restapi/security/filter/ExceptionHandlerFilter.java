package com.nmp.restapi.security.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.nmp.restapi.exception.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (EntityNotFoundException exception) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().println("Username not found.");
            response.getWriter().flush();
        } catch (JWTVerificationException exception) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().println("JWT token is not valid.");
            response.getWriter().flush();
        } catch (RuntimeException exception) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Bad request.");
            response.getWriter().flush();
        }

    }
}
