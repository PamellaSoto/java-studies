package com.studies.spring_boot.expenses;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.studies.spring_boot.users.UserModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name="tb_expenses")
public class ExpenseModel {

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private UserModel user;

  @Id
  @GeneratedValue(generator="UUID")
  private UUID id;

  @Column(length = 50)
  private String expenseTitle;
  private Float expenseAmount;
  private String expenseCategory;
  
  @CreationTimestamp
  private LocalDateTime createdAt;
}
