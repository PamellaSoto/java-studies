package com.studies.spring_boot.expenses;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


public interface IExpenseRepository extends JpaRepository<ExpenseModel, UUID>{

    public List<ExpenseModel> findByUserId(UUID userId);
  
}
