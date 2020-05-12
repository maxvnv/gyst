package com.max.gyst.expense;

import com.max.gyst.category.Subcategory;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    private LocalDateTime dateTime = LocalDateTime.now();
    @NotBlank
    @Column(name = "TITLE")
    private String title;
    @NotNull
    @Column(name = "AMOUNT")
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "SUBCATEGORY_ID")
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

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public Expense withTitle(String title) {
        this.title = title;
        return this;
    }

    public Expense withAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public Expense withSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
        return this;
    }

    ExpenseDto toExpenseDto() {
        return new ExpenseDto(getTitle(), getAmount(), getSubcategory().getId(), getDateTime());
    }
}