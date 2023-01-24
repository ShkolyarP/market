package com.paltvlad.spring.market.entities;


import com.paltvlad.spring.market.dtos.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    private List<ProductDto> userCart = new ArrayList<ProductDto>();

    public void addToCart(ProductDto productDto) {
        if (!userCart.contains(productDto)) {
            productDto.setSum(productDto.getAmount() * productDto.getPrice());
            userCart.add(productDto);
        } else {
            for (ProductDto p:
                 userCart) {
                if (p.getId().equals(productDto.getId())){
                    productDto = p;
                }
            }

            addMoreToCart(productDto);
        }


    }

    public void addMoreToCart(ProductDto productDto) {

        userCart.remove(productDto);
        productDto.setAmount(productDto.getAmount() + 1);
        productDto.setSum(productDto.getAmount() * productDto.getPrice());
        userCart.add(productDto);
    }

}
