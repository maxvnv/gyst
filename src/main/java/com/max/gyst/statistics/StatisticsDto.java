package com.max.gyst.statistics;

public class StatisticsDto {

    private boolean isDailyInBudget;

    StatisticsDto(boolean isDailyInBudget) {
        this.isDailyInBudget = isDailyInBudget;
    }

    public boolean isDailyInBudget() {
        return isDailyInBudget;
    }
}
