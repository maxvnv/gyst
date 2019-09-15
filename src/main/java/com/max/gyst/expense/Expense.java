package com.max.gyst.expense;

import com.max.gyst.category.Category;
import com.max.gyst.category.Subcategory;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static javax.persistence.EnumType.STRING;

@Entity
public class Expense {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime dateTime = LocalDateTime.now();
    @NotBlank
    private String title;
    @NotNull
    private Double amount;
    @Enumerated(STRING)
    @NotNull
    private Category category;
    @Enumerated(STRING)
    private Subcategory subcategory;

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getTitle() {
        return title;
    }

    public Double getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }
}