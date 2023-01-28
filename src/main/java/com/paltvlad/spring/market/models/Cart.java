package com.paltvlad.spring.market.models;

import com.paltvlad.spring.market.aop.MeasureExecutionTime;
import com.paltvlad.spring.market.dtos.ProductDto;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Component
@Data
public class Cart {

    private List<CartItem> items;

    private double totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }


    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void recalculate() {
        totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getPrice();
        }
    }

    @MeasureExecutionTime
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
}
