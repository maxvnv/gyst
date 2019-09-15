package com.max.gyst.util;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class FilterMaster<T> {

    private List<T> source;

    public FilterMaster(List<T> expenses) {
        this.source = expenses;
    }

    public FilterMaster<T> filterIfNotNull(Function<T, Object> extractor, Object target) {
        if (target == null) {
            return this;
        }
        source = source.stream()
                .filter(x -> target.equals(extractor.apply(x)))
                .collect(toList());
        return this;
    }

    public List<T> getSource() {
        return source;
    }
}
