package com.studies.spring_boot.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

// Public / Private / Protected
@RestController
@RequestMapping("/api")
public class UserController {

  // responsible for the life cycle
  @Autowired
  private IUserRepository userRepository;

  // @RequestBody -> annotation to have the request body read into an Object object
  @PostMapping("/register")
  public ResponseEntity create(@RequestBody UserModel userModel) {
    var user = this.userRepository.findByUsername(userModel.getUsername());

    if (user != null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                           .body("Username already exists.");
    }
    var passwordHash = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
    userModel.setPassword(passwordHash);
    
    this.userRepository.save(userModel);
    return ResponseEntity.status(HttpStatus.CREATED)
                         .body("New user created.");
  }
}
