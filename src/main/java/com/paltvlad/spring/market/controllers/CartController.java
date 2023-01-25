package com.paltvlad.spring.market.controllers;


import com.paltvlad.spring.market.dtos.Cart;
import com.paltvlad.spring.market.dtos.ProductDto;
import com.paltvlad.spring.market.services.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cartService.addToCard(id);
    }

    @GetMapping
    public Cart getCurrentCart(){
        return cartService.getCurrentCart();
    }

    @GetMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {

        cartService.deleteById(id);
    }

    @GetMapping("/delete")
    public void deleteAllFromCart() {

        cartService.deleteAllFromCart();
    }

    @GetMapping("/change_quantity")
    public void changePrice(@RequestParam Long productId, @RequestParam Integer delta) {

        cartService.changeQuantity(productId, delta);
    }
}
