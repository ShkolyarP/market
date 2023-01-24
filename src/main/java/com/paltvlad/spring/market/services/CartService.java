package com.paltvlad.spring.market.services;


import com.paltvlad.spring.market.converters.ProductConverter;
import com.paltvlad.spring.market.dtos.ProductDto;
import com.paltvlad.spring.market.entities.Cart;
import com.paltvlad.spring.market.entities.Product;
import com.paltvlad.spring.market.exeptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private final ProductConverter productConverter;


    private final Cart cart;

    public List<ProductDto> addToCard(Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
        ProductDto productDto = productConverter.EntityToDto(product);
        cart.addToCart(productDto);
        return cart.getUserCart();

    }
}
