package com.lacortez.expensetrackerapi.repository;

import com.lacortez.expensetrackerapi.model.Expense;
import com.lacortez.expensetrackerapi.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUser(User user);

    List<Expense> findByExpenseId(Long expenseId);

}
