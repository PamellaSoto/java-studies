package com.studies.spring_boot.config;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.studies.spring_boot.auth.JwtTokenService;
import com.studies.spring_boot.users.IUserRepository;
import com.studies.spring_boot.users.UserModel;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterJwtAuth extends OncePerRequestFilter {

    private final JwtTokenService tokenService;
    private final IUserRepository userRepository;

    public FilterJwtAuth(JwtTokenService tokenService, IUserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        var authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            var token = authHeader.substring(7);

            if (tokenService.validateToken(token)) {
                var userId = tokenService.getUserIdFromToken(token);
                UserModel user = userRepository.findById(UUID.fromString(userId)).orElse(null);

                if (user != null) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                    request.setAttribute("userId", user.getId());
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
