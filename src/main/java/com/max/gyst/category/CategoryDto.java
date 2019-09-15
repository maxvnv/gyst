package com.max.gyst.category;

import java.util.List;

class CategoryDto {

    private Category category;
    private List<Subcategory> subcategories;

    CategoryDto(Category category, List<Subcategory> subcategories) {
        this.category = category;
        this.subcategories = subcategories;
    }

    public Category getCategory() {
        return category;
    }

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }
}
