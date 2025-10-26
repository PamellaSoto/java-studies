package com.studies.spring_boot.auth;

import lombok.Data;

@Data
public class LoginRequest {
  private String username;
  private String password;
}
