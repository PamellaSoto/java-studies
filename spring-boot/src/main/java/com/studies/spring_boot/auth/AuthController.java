package com.studies.spring_boot.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studies.spring_boot.users.IUserRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;


@RestController
@RequestMapping("/api")
public class AuthController {

  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private JwtTokenService tokenService;
  
  @PostMapping("/login")
  public ResponseEntity login(@RequestBody LoginRequest request) {
    var user = this.userRepository.findByUsername(request.getUsername());

    if (user == null || !BCrypt.verifyer().verify(request.getPassword().toCharArray(), user.getPassword()).verified) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
      String token = tokenService.generateToken(user);

      return ResponseEntity.status(HttpStatus.OK)
                           .body(token);
  }
  
}
