package com.paltvlad.market.carts.models;


import com.paltvlad.market.api.ProductDto;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Component
@Data
public class Cart {

    private List<CartItem> items;

    private BigDecimal totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }



    public void recalculate() {
        totalPrice = BigDecimal.ZERO;
        for (CartItem item : items) {
            totalPrice = totalPrice.add(item.getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
    }


    public void addToCart(ProductDto productDto) {

        for (CartItem item :
                items) {
            if (item.getProductId().equals(productDto.getId())) {
                item.changeQuantity(1);
                recalculate();
                return;
            }
        }

        items.add(new CartItem(productDto.getId(), productDto.getTitle(), 1, productDto.getPrice(), productDto.getPrice()));

        recalculate();

    }

    public CartItem findById(Long id) {
        return items.stream().filter(i -> i.getProductId().equals(id)).findFirst().get();
    }

    public void deleteById(Long id) {
        if (items.removeIf((i -> i.getProductId().equals(id)))) {
            recalculate();
        }
    }


    public void clearCart() {
        items.clear();
        recalculate();
    }

    public void addItem(CartItem cartItem) {
        items.add(cartItem);
    }
}
