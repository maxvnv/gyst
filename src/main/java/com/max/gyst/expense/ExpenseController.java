package com.max.gyst.expense;

import com.max.gyst.category.Category;
import com.max.gyst.category.Subcategory;
import com.max.gyst.util.FilterMaster;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class ExpenseController {

    private final ExpensesRepository expensesRepository;

    public ExpenseController(ExpensesRepository expensesRepository) {
        this.expensesRepository = expensesRepository;
    }

    @PatchMapping("expense")
    public ResponseEntity enterExpense(@RequestBody @Valid Expense expense) {
        expensesRepository.save(expense);

        return ResponseEntity.created(URI.create(String.format("/expense/%s", expense.getId()))).build();
    }

    @GetMapping("expense")
    public List<Expense> getExpenses(
            @RequestParam(required = false, name = "category") Category category,
            @RequestParam(required = false, name = "subcategory") Subcategory subcategory
    ) {
        return new FilterMaster<>(expensesRepository.findAll())
                .filterIfNotNull(Expense::getCategory, category)
                .filterIfNotNull(Expense::getSubcategory, subcategory)
                .getSource();
    }

    @GetMapping("expense/{id}")
    public Expense getExpense(@PathVariable(value = "id", required = false) Long id) {
        return expensesRepository.findById(id).orElseThrow(ExpenseNotFoundException::new);
    }
}