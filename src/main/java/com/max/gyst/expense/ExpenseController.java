package com.max.gyst.expense;

import com.max.gyst.category.Category;
import com.max.gyst.category.CategoryNotFoundException;
import com.max.gyst.category.CategoryRepository;
import com.max.gyst.category.Subcategory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
public class ExpenseController {

    private final ExpensesRepository expensesRepository;
    private final CategoryRepository categoryRepository;

    public ExpenseController(ExpensesRepository expensesRepository, CategoryRepository categoryRepository) {
        this.expensesRepository = expensesRepository;
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("expense")
    public Expense enterExpense(@RequestBody @Valid ExpenseDto expenseDto) {
        Subcategory subCategory = categoryRepository.findAll().stream()
                .map(Category::getSubcategories)
                .flatMap(Collection::stream)
                .filter(subcategory -> subcategory.getId().equals(expenseDto.getSubcategoryId()))
                .findFirst()
                .orElseThrow(CategoryNotFoundException::new);

        Expense expense = new Expense()
                .withTitle(expenseDto.getTitle())
                .withAmount(expenseDto.getAmount())
                .withSubcategory(subCategory);

        return expensesRepository.save(expense);
    }

    @GetMapping("expense/{id}")
    public Expense getExpense(@PathVariable Long id) {
        return expensesRepository.findById(id).orElseThrow(ExpenseNotFoundException::new);
    }

    @GetMapping("expense")
    public List<ExpenseDto> getExpenses() {
        return expensesRepository.findAll().stream()
                .map(Expense::toExpenseDto)
                .collect(toList());
    }

}