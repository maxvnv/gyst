package com.max.gyst.category;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.max.gyst.user.User;

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

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private List<Subcategory> subcategory = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }


    Category withName(String name) {
        this.name = name;
        return this;
    }

    Category withUser(User user) {
        this.user = user;
        return this;
    }

    public List<Subcategory> getSubcategory() {
        return subcategory;
    }
}
