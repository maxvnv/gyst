package com.max.gyst.category;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("category")
    public Category createCategory(@RequestBody String name) {
        return categoryService.createCategoryWithName(name);
    }

    @PostMapping("category/{categoryId}/subcategory")
    public Category createSubcategory(@PathVariable Long categoryId, @RequestBody String name) {
        Category category = categoryService.getCategoryById(categoryId);
        category.getSubcategories().add(new Subcategory().withName(name));
        return categoryService.updateCategory(category);
    }

    @GetMapping("category")
    public List<Category> getCategories() {
        return categoryService.getAllCategories();
    }
}
