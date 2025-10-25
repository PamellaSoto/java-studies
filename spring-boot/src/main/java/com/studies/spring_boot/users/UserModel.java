package com.studies.spring_boot.users;

import lombok.Data;

// lombok @Data creates the getters and setters 
// lombok @Getter creates only getters
// lombok @Setter creates only setters

@Data
public class UserModel {
  private String name;
  private String username;
  private String password;

}
