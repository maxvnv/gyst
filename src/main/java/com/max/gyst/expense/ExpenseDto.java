package com.max.gyst.expense;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

class ExpenseDto {

    @JsonProperty("title")
    private String title;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("subcategoryId")
    private Long subcategoryId;

    @JsonProperty("dateTime")
    private LocalDateTime dateTime;

    public ExpenseDto(String title, Double amount, Long subcategoryId, LocalDateTime dateTime) {
        this.title = title;
        this.amount = amount;
        this.subcategoryId = subcategoryId;
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public Double getAmount() {
        return amount;
    }

    public Long getSubcategoryId() {
        return subcategoryId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
