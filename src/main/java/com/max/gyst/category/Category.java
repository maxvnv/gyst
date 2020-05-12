package com.max.gyst.category;

import com.google.common.annotations.VisibleForTesting;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank
    @Column(name = "NAME")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private List<Subcategory> subcategories = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Subcategory> getSubcategories() {
        return subcategories;
    }

    Category withName(String name) {
        this.name = name;
        return this;
    }

    public Category withSubcategories(List<Subcategory> subcategory) {
        this.subcategories = subcategory;
        return this;
    }

    @VisibleForTesting
    public Category withId(Long id) {
        this.id = id;
        return this;
    }
}
