package com.paltvlad.market.carts.controllers;

import com.paltvlad.market.api.CartDto;
import com.paltvlad.market.carts.converters.CartConverter;
import com.paltvlad.market.carts.models.Cart;
import com.paltvlad.market.carts.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
@CrossOrigin("*")
public class CartController {

    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cartService.addToCard(id);
    }

    @GetMapping
    public CartDto getCurrentCart(){
        return cartConverter.modelToDto(cartService.getCurrentCart());
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
