package com.max.gyst.category;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    Category createCategoryWithName(String categoryName) {
        return categoryRepository.save(new Category().withName(categoryName));
    }

    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category getCategoryById(long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(CategoryNotFoundException::new);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
