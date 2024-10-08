package com.lacortez.expensetrackerapi.service;

import com.lacortez.expensetrackerapi.exception.ExpenseNotFoundException;
import com.lacortez.expensetrackerapi.model.Expense;

import java.util.List;

public interface ExpenseService {

    List<Expense> getExpenseList(Long userId);
    Expense getExpense(Long expenseId) throws ExpenseNotFoundException;
    Expense addExpense(Expense expense) throws Exception;
    Expense updateExpense(Expense newExpense) throws Exception;
    Expense deleteExpense(Long expenseId);

}
