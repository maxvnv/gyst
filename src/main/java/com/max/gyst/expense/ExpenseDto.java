package com.max.gyst.expense;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExpenseDto {

    private String title;
    private Double amount;
    @JsonProperty("subcategoryId")
    private Long subcategoryId;

    public String getTitle() {
        return title;
    }

    public Double getAmount() {
        return amount;
    }

    public Long getSubcategoryId() {
        return subcategoryId;
    }
}
