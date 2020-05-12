package com.max.gyst.category;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;

import static java.lang.Long.parseLong;
import static org.assertj.core.api.Assertions.assertThat;

@Component
public class TestCategoryRepositoryHelper {

    private final AtomicLong idGenerator = new AtomicLong(1);
    private Map<Long, Category> savedCategories = new HashMap<>();

    public List<Category> getSavedCategories() {
        return new ArrayList<>(savedCategories.values());
    }

    public Optional<Category> getCategoryById(Long categoryId) {
        return Optional.ofNullable(savedCategories.get(categoryId));
    }

    public Category saveCategory(Category category) {
        if (categoryExists(category)) {
            Category categoryToPersist = new Category()
                    .withId(category.getId())
                    .withName(category.getName())
                    .withSubcategories(category.getSubcategories());
            savedCategories.put(categoryToPersist.getId(), categoryToPersist);
            return categoryToPersist;
        } else {
            Category categoryToPersist = new Category().withId(idGenerator.getAndIncrement()).withName(category.getName());
            savedCategories.put(categoryToPersist.getId(), categoryToPersist);
            return categoryToPersist;
        }
    }

    private boolean categoryExists(Category category) {
        return getCategoryById(category.getId()).isPresent();
    }

    public CategoryAssert assertThatCategory() {
        return new CategoryAssert();
    }

    class CategoryAssert {
        private List<Predicate<Category>> predicates = new ArrayList<>();

        public CategoryAssert withName(String name) {
            predicates.add(category -> category.getName().equals(name));
            return this;
        }

        public CategoryAssert withId(String id) {
            predicates.add(category -> category.getId() == parseLong(id));
            return this;
        }

        void hasBeenStored() {
            Optional<Category> savedCategory = savedCategories.values().stream()
                    .filter(predicates.stream().reduce(x -> true, Predicate::and))
                    .findFirst();
            assertThat(savedCategory).isNotEmpty();
        }
    }
}
