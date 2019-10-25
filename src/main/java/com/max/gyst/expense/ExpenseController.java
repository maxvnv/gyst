package com.max.gyst.expense;

import com.max.gyst.category.Category;
import com.max.gyst.category.CategoryNotFoundException;
import com.max.gyst.category.Subcategory;
import com.max.gyst.user.User;
import com.max.gyst.user.UserNotFoundException;
import com.max.gyst.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

import static java.lang.Long.parseLong;
import static java.util.stream.Collectors.toList;

@RestController
public class ExpenseController {

    private final ExpensesRepository expensesRepository;
    private final UserRepository userRepository;

    public ExpenseController(ExpensesRepository expensesRepository, UserRepository userRepository) {
        this.expensesRepository = expensesRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("expense")
    public ResponseEntity enterExpense(@RequestHeader String userId, @RequestBody @Valid ExpenseDto expense) {
        User user = userRepository.findById(parseLong(userId)).orElseThrow(UserNotFoundException::new);
        Subcategory subcategoryToPersist = user.getCategories().stream()
                .map(Category::getSubcategory)
                .flatMap(Collection::stream)
                .filter(subcategory -> subcategory.getId().equals(expense.getSubcategoryId()))
                .findFirst()
                .orElseThrow(CategoryNotFoundException::new);


        Expense expenseToPersist = new Expense()
                .withTitle(expense.getTitle())
                .withAmount(expense.getAmount())
                .withUser(user)
                .withSubcategory(subcategoryToPersist);

        expensesRepository.save(expenseToPersist);

        return ResponseEntity.ok(expenseToPersist);
    }


    @GetMapping("expense/{id}")
    public Expense getExpense(@PathVariable(value = "id", required = false) Long id) {
        return expensesRepository.findById(id).orElseThrow(ExpenseNotFoundException::new);
    }

    @GetMapping("expense")
    public List<ExpenseDto> getExpense(@RequestHeader String userId) {
        User user = userRepository.findById(parseLong(userId)).orElseThrow(UserNotFoundException::new);
        return expensesRepository.findAllByUser(user).stream()
                .map(this::toExpenseDto)
                .collect(toList());
    }

    private ExpenseDto toExpenseDto(Expense expense) {
        return new ExpenseDto(expense.getTitle(), expense.getAmount(), expense.getSubcategory().getId(), expense.getDateTime());
    }
}