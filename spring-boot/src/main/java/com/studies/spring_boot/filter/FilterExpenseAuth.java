package com.studies.spring_boot.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.studies.spring_boot.users.IUserRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// makes this class accessible to spring boot
@Component
public class FilterExpenseAuth extends OncePerRequestFilter {

  @Autowired
  private IUserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, 
                                  HttpServletResponse response, 
                                  FilterChain filterChain) 
    throws ServletException, IOException {

    
    var servletPath = request.getServletPath();

    if (servletPath.contains("/api/expenses")) {
      var auth = request.getHeader("Authorization");

      if (auth == null || !auth.startsWith("Basic ")) {
        response.sendError(401, "Missing or invalid Authorization header.");
        return;
      }
      
      // decode Base64 credentials
      var baseCredentials = auth.substring("Basic".length()).trim();
      var authDecoded = new String(Base64.getDecoder().decode(baseCredentials));

      String[] credentials = authDecoded.split(":");
      String username = credentials[0];
      String password = credentials[1];

      // find user
      var user = this.userRepository.findByUsername(username);
      if (user == null) {
        response.sendError(401, "User not allowed.");
        return;
      }
      
      // check password
      var passwordResult = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
      if (!passwordResult.verified) {
        response.sendError(401, "User not allowed.");
        return;
      }
      
      // store userID in request
      request.setAttribute("userId", user.getId());
    }

    filterChain.doFilter(request, response);
  }
}
