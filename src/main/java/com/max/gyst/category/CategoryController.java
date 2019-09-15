package com.max.gyst.category;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@RestController
public class CategoryController {

    @GetMapping("/category")
    public List<CategoryDto> getCategories() {
        return Stream.of(Subcategory.values())
                .collect(groupingBy(Subcategory::getCategory))
                .entrySet()
                .stream()
                .map(x -> new CategoryDto(x.getKey(), x.getValue()))
                .collect(toList());
    }
}
