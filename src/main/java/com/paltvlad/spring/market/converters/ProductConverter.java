package com.paltvlad.spring.market.converters;


import com.paltvlad.spring.market.dtos.ProductDto;
import com.paltvlad.spring.market.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public Product dtoToEntity(ProductDto productDto){
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getPrice());
    }

    public ProductDto EntityToDto(Product product){
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice());
    }

}
