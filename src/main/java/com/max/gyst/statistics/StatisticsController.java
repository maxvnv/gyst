package com.max.gyst.statistics;

import com.max.gyst.expense.Expense;
import com.max.gyst.expense.ExpensesRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class StatisticsController {

    private final ExpensesRepository expensesRepository;

    @Value("${gyst.budgetlimit.daily}")
    private Double dailyBudget;

    public StatisticsController(ExpensesRepository expensesRepository) {
        this.expensesRepository = expensesRepository;
    }

    @GetMapping("/statistics")
    public StatisticsDto getStatistics() {
        double dailySum = expensesRepository.findAll().stream()
                .filter(expense -> expense.getDateTime().getDayOfYear() == LocalDateTime.now().getDayOfYear())
                .mapToDouble(Expense::getAmount)
                .sum();
        return new StatisticsDto(dailySum < dailyBudget);

    }
}
