package com.max.gyst.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.max.gyst.category.Category;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class User {

    @JsonProperty
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @JsonProperty
    @NotBlank
    @Column(name = "FIRST_NAME")
    private String firstname;

    @JsonProperty
    @NotBlank
    @Column(name = "LAST_NAME")
    private String lastname;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Category> categories;

    Long getId() {
        return id;
    }

    String getFirstname() {
        return firstname;
    }

    String getLastname() {
        return lastname;
    }

    public List<Category> getCategories() {
        return categories;
    }
}
