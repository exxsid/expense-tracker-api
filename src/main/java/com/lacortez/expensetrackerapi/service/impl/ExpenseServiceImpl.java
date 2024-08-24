package com.lacortez.expensetrackerapi.service.impl;

import com.lacortez.expensetrackerapi.exception.ExpenseNotFoundException;
import com.lacortez.expensetrackerapi.model.Expense;
import com.lacortez.expensetrackerapi.model.User;
import com.lacortez.expensetrackerapi.repository.ExpenseRepository;
import com.lacortez.expensetrackerapi.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<Expense> getExpenseList(Long userId) {
        User user = new User();
        user.setUserId(userId);
        List<Expense> expenses = expenseRepository.findByUser(user);
        return expenses;
    }

    @Override
    public Expense getExpense(Long expenseId) throws ExpenseNotFoundException {
        List<Expense> expense = expenseRepository.findByExpenseId(expenseId);
        if (expense.isEmpty()) throw new ExpenseNotFoundException("No expense found");
        return expense.get(0);
    }

    @Override
    public Expense addExpense(Expense expense) throws Exception {
        if (expense.getAmount() <= 0) {
            throw new Exception("Invalid expense amount");
        }
        Expense newExpense = expenseRepository.save(expense);

        return newExpense;
    }

    @Override
    public Expense updateExpense(Expense newExpense) throws Exception{
        if (newExpense.getAmount() < 0) {
            throw new Exception("Invalid expense amount");
        }
        int updatedExpense = expenseRepository.updateExpense(
                newExpense.getExpenseId(),
                newExpense.getAmount(),
                newExpense.getCategory(),
                newExpense.getNote(),
                newExpense.getDescription()
        );
        if (updatedExpense < 1) {
            return null;
        }
        return newExpense;
    }

    @Override
    public Expense deleteExpense(Long expenseId) {
        return null;
    }


}
