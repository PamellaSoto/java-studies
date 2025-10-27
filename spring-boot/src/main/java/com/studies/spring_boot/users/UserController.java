package com.studies.spring_boot.users;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


// Public / Private / Protected
@RestController
@RequestMapping("/api")
public class UserController {

  // @PathVariable -> Handles params in a request via the url path
  @GetMapping("/result/{id}")
  public String getUserData(@PathVariable String id) {
      return "Selected user ID: " + id;
  }
  
  // @RequestParam -> Handles a single query
  // .../api/result?query=variable
  @GetMapping("/result")
  public String getQueryParams(@RequestParam String param) {
    return "You searched for: " + param;
  } 

  // @RequestParam -> Handles multiple queries
  // .../api/results?name=Pamella&age=21&area=Full%20stack%20developer  
  @GetMapping("/results")
  public String getMultipleQueryParams(@RequestParam Map<String, String> allParams) {
    return "You searched for: " + allParams.entrySet();
  }
  
  // @RequestBody -> Handles params in a request body
  @PostMapping("/new")
  // public void create(@RequestBody String username) {
  public void create(@RequestBody UserModel userModel) {
    System.out.println("New user created: " + userModel.getUsername());
  }

  // @RequestHeader -> Handles a single param in a request via header
  @GetMapping("/token")
  public String getToken(@RequestHeader("token") String token) {
    return "User token: " + token;
  }

  // @RequestHeader -> Returns all data inside a header of a requisition
  @GetMapping("/header")
  public String getMultipleHeaderParams(@RequestHeader Map<String, String> params) {
    return "Header params: " + params.entrySet();
  }

  //@ResponseEntity -> Returns HTTP status code and custom body messages
  @GetMapping("/success")
  public ResponseEntity<Object> success() {
    return ResponseEntity.status(HttpStatus.OK).body("success");
  }

  @GetMapping("/error")
  public ResponseEntity<Object> error() {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
  }
}
