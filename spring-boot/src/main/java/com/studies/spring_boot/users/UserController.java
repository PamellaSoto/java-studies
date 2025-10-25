package com.studies.spring_boot.users;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Public / Private / Protected
@RestController
@RequestMapping("/api/users")
public class UserController {

  // @RequestBody -> annotation to have the request body read into an Object object
  @PostMapping
  public void create(@RequestBody UserModel userModel) {
    System.out.println(userModel.getUsername());
  }
}
