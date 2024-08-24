package com.lacortez.expensetrackerapi.repository;

import com.lacortez.expensetrackerapi.model.Expense;
import com.lacortez.expensetrackerapi.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUser(User user);

    List<Expense> findByExpenseId(Long expenseId);

    @Transactional
    @Modifying
    @Query("UPDATE Expense e " +
            "SET e.amount = :amount, e.category = :category, " +
            "e.note = :note, e.description = :description " +
            "WHERE e.expenseId = :id")
    int updateExpense(@Param("id") Long id,
                      @Param("amount") Double amount,
                      @Param("category") String category,
                      @Param("note") String note,
                      @Param("description") String description);

}
