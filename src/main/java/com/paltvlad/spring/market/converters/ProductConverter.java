package com.paltvlad.spring.market.converters;


import com.paltvlad.spring.market.dtos.ProductDto;
import com.paltvlad.spring.market.entities.Category;
import com.paltvlad.spring.market.entities.Product;
import com.paltvlad.spring.market.exeptions.ResourceNotFoundException;
import com.paltvlad.spring.market.services.CategoryService;
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
