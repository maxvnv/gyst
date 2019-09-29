package com.max.gyst.category;

import com.max.gyst.user.User;
import com.max.gyst.user.UserNotFoundException;
import com.max.gyst.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.List;

import static java.lang.Long.parseLong;

@RestController
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public CategoryController(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("category")
    public ResponseEntity<Category> addCategory(
            @RequestHeader(name = "userId", defaultValue = "") @Pattern(regexp = "\\d+") String userId,
            @RequestBody String name) {
        User user = userRepository.findById(parseLong(userId)).orElseThrow(UserNotFoundException::new);

        Category category = new Category().withName(name).withUser(user);

        categoryRepository.save(category);

        return ResponseEntity.ok(category);
    }

    @PostMapping("category/{categoryId}/subcategory")
    public ResponseEntity<Category> addSubcategory(
            @PathVariable String categoryId,
            @RequestBody String name) {
        Category category = categoryRepository.findById(parseLong(categoryId)).orElseThrow(CategoryNotFoundException::new);
        Subcategory subcategory = new Subcategory().withName(name);
        category.getSubcategory().add(subcategory);

        categoryRepository.save(category);

        return ResponseEntity.ok(category);
    }

    @GetMapping("category")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
}
