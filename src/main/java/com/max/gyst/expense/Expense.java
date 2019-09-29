package com.max.gyst.expense;

import com.max.gyst.category.Subcategory;
import com.max.gyst.user.User;

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
    @ManyToOne
    @JoinColumn(name = "OWNING_USER")
    private User user;

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

    public User getUser() {
        return user;
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

    public Expense withUser(User user) {
        this.user = user;
        return this;
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
}