package com.paltvlad.spring.market.validators;


import com.paltvlad.spring.market.dtos.ProductDto;
import com.paltvlad.spring.market.exeptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    public void validate(ProductDto productDto) {
        List<String> errors = new ArrayList<>();
        if (productDto.getPrice() < 0) {
            errors.add("Цена продукта не может быть меньше 0");
        }
        if (productDto.getTitle().isBlank()) {
            errors.add("Продукт не может иметь пустое название");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
