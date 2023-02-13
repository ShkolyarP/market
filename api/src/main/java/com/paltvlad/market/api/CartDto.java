package com.paltvlad.market.api;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class CartDto {

    private List<CartItemDto> items;

    private double totalPrice;

    public List<CartItemDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
