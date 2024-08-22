package com.lacortez.expensetrackerapi.controller;

import com.lacortez.expensetrackerapi.model.Expense;
import com.lacortez.expensetrackerapi.service.impl.ExpenseServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/expenses")
public class ExpenseController {

    private ExpenseServiceImpl expenseService;

    public ExpenseController(ExpenseServiceImpl expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/{id}")
    public List<Expense> getExpenses(
            @PathVariable Long userId
    ) {
        System.out.println("NAGPRINT NA");
        return expenseService.getExpenseList(userId);
    }
}
