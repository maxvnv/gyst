package com.max.gyst.category;

import static com.max.gyst.category.Category.*;

public enum Subcategory {

    HEETS(CIGARETTES),

    HOME(GROCERIES),

    RENT(BILLS),
    FLAT_BILLS(BILLS),
    INTERNET(BILLS),
    ENTERTAINMENT(BILLS),

    TAXI(TRANSPORT),
    PUBLIC(TRANSPORT),

    SQUASH(SPORT),

    SNACK(FOOD),
    WORK_LUNCH(FOOD),
    RESTAURANT(FOOD),
    CAFE(FOOD),
    DELIVERY(FOOD);

    private Category category;

    Subcategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }
}
