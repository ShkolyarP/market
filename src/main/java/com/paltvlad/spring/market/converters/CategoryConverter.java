package com.paltvlad.spring.market.converters;


import com.paltvlad.spring.market.dtos.CategoryDto;
import com.paltvlad.spring.market.entities.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryConverter {

    private final ProductConverter productConverter;


    public CategoryDto EntityToDto(Category category) {
        CategoryDto c = new CategoryDto();
        c.setId(category.getId());
        c.setTitle(category.getTitle());
        c.setProductsDto(category.getProducts().stream().map(productConverter::EntityToDto).collect(Collectors.toList()));

        return c;
    }

}
