package com.lacortez.expensetrackerapi.controller;

import com.lacortez.expensetrackerapi.exception.ExpenseNotFoundException;
import com.lacortez.expensetrackerapi.model.Expense;
import com.lacortez.expensetrackerapi.model.User;
import com.lacortez.expensetrackerapi.service.impl.ExpenseServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
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

    @PostMapping("/add")
    public ResponseEntity<Expense> addExpense(
            @RequestParam Double amount,
            @RequestParam String category,
            @RequestParam String note,
            @RequestParam String description,
            @RequestParam Long userId
    ){
        try {
            User user = new User();
            user.setUserId(userId);

            Expense expense = new Expense();
            expense.setAmount(amount);
            expense.setCategory(category);
            expense.setNote(note);
            expense.setDescription(description);
            expense.setUser(user);

            Expense newExpense = expenseService.addExpense(expense);

            return new ResponseEntity<>(newExpense, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Expense> updateExpense(@RequestBody Expense newExpense) {
        try {
            Expense updatedExpense = expenseService.updateExpense(newExpense);
            if (updatedExpense == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(updatedExpense, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
