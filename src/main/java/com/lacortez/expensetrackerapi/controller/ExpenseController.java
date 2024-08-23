package com.lacortez.expensetrackerapi.controller;

import com.lacortez.expensetrackerapi.exception.ExpenseNotFoundException;
import com.lacortez.expensetrackerapi.model.Expense;
import com.lacortez.expensetrackerapi.service.impl.ExpenseServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/expenses")
public class ExpenseController {

    private ExpenseServiceImpl expenseService;

    public ExpenseController(ExpenseServiceImpl expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/{userId}")
    public List<Expense> getExpenses(
            @PathVariable Long userId
    ) {
        return expenseService.getExpenseList(userId);
    }

    @GetMapping("/details/{expenseId}")
    public ResponseEntity<Expense> getExpenseDetails(
            @PathVariable Long expenseId
    ) {
        try {
            Expense expense = expenseService.getExpense(expenseId);
            return new ResponseEntity<>(expense, HttpStatus.OK);
        } catch (ExpenseNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
