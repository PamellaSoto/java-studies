package com.studies.spring_boot.users;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

// lombok @Data creates the getters and setters 
// lombok @Getter creates only getters
// lombok @Setter creates only setters

@Data
// associate the Model with a db entity
@Entity(name="tb_users")
public class UserModel {

  @Id
  @GeneratedValue(generator="UUID")
  private UUID id;

  private String name;

  /* @Column is responsible for :
    creating a name for the column
    adding constraints
  */
  @Column(unique= true)
  private String username;


  private String password;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
