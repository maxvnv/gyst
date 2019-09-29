package com.max.gyst.expense;

import com.max.gyst.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpensesRepository extends JpaRepository<Expense, Long> {

    List<Expense> findAllByUser(User user);
}
