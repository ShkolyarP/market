package com.paltvlad.market.core.validators;

import com.paltvlad.market.api.ProductDto;
import com.paltvlad.market.core.exeptions.ValidationException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    public void validate(ProductDto productDto) {
        List<String> errors = new ArrayList<>();
        if (productDto.getPrice().compareTo(BigDecimal.ZERO) < 0) {
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
