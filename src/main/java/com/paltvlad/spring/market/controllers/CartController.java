package com.paltvlad.spring.market.controllers;


import com.paltvlad.spring.market.models.Cart;
import com.paltvlad.spring.market.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/remove/{id}")
    public void deleteById(@PathVariable Long id) {

        cartService.deleteById(id);
    }

    @GetMapping("/clear")
    public void clearCart() {

        cartService.deleteAllFromCart();
    }

    @GetMapping("/change_quantity")
    public void changePrice(@RequestParam Long productId, @RequestParam Integer delta) {

        cartService.changeQuantity(productId, delta);
    }
}
