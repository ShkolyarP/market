package com.paltvlad.spring.market.dtos;

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

    public void addToCart(ProductDto productDto) {

        boolean finding = false;

        for (CartItem item :
                items) {
            if (item.getProductId().equals(productDto.getId())) {
                item.setQuantity(item.getQuantity() + 1);
                item.setPrice(item.getPricePerProduct() * item.getQuantity());
                finding = true;
            }
        }


        if (!finding) {
            items.add(new CartItem(productDto.getId(), productDto.getTitle(), 1, productDto.getPrice(), productDto.getPrice()));
        }

        recalculate();

    }

    public CartItem findById(Long id){
        return items.stream().filter(i -> i.getProductId().equals(id)).findFirst().get();
    }

    public void deleteById(Long id) {

        items.removeIf((i -> i.getProductId().equals(id)));
        recalculate();
    }


    public void deleteAllFromCart() {
        items.clear();
        recalculate();
    }
}
