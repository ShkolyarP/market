package com.paltvlad.spring.market.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {


    private Long productId;
    private String productTitle;
    private int quantity;
    private double pricePerProduct;
    private double price;
}
