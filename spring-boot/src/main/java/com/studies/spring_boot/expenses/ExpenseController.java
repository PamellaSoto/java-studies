package com.studies.spring_boot.expenses;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studies.spring_boot.users.IUserRepository;
import com.studies.spring_boot.users.UserModel;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

  @Autowired
  private IExpenseRepository expenseRepository;

  @Autowired
  private IUserRepository userRepository;

  @PostMapping
  public ResponseEntity create(@RequestBody ExpenseModel expenseModel, HttpServletRequest request) {
    UUID userId = (UUID) request.getAttribute("userId");

    UserModel user = userRepository.getReferenceById(userId);
    System.out.println("UserId in request: " + request.getAttribute("userId"));
    expenseModel.setUser(user);
    
    var expenseCreated = this.expenseRepository.save(expenseModel);
    return ResponseEntity.status(HttpStatus.CREATED)
                         .body(expenseCreated);
  }

  @GetMapping
  public List<ExpenseModel> list(HttpServletRequest request) {
      UUID userId = (UUID) request.getAttribute("userId");

      List<ExpenseModel> expenses = expenseRepository.findByUserId(userId);

      return expenses;
  }

}
