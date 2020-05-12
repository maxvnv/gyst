package com.max.gyst.category;

import javax.persistence.*;

@Entity(name = "SUBCATEGORY")
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    Subcategory withName(String name) {
        this.name = name;
        return this;
    }
}
