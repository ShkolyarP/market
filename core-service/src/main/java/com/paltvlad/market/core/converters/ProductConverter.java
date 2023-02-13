package com.paltvlad.market.core.converters;


import com.paltvlad.market.api.ProductDto;
import com.paltvlad.market.api.ResourceNotFoundException;
import com.paltvlad.market.core.entities.Category;
import com.paltvlad.market.core.entities.Product;

import com.paltvlad.market.core.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductConverter {

    private final CategoryService categoryService;

    public Product dtoToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());

        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        product.setCategory(category);

        return new Product(productDto.getId(), productDto.getTitle(), productDto.getPrice());
    }

    public ProductDto EntityToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice(), product.getCategory().getTitle());
    }

}
