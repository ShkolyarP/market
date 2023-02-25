package com.paltvlad.market.carts.controllers;

import com.paltvlad.market.api.CartDto;
import com.paltvlad.market.api.StringResponse;
import com.paltvlad.market.carts.converters.CartConverter;
import com.paltvlad.market.carts.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/generate_uuid")
    public StringResponse generateUuid(){
        return new StringResponse(UUID.randomUUID().toString());
    }

    @GetMapping("/{uuid}/add/{id}")
    public void addToCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid, @PathVariable Long id) {
        String targetUuid = getCartUuid(username, uuid);
        cartService.addToCard(targetUuid, id);
    }

    @GetMapping("/{uuid}")
    public CartDto getCurrentCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid) {
        String targetUuid = getCartUuid(username, uuid);
        return cartConverter.modelToDto(cartService.getCurrentCart(targetUuid));
    }

    @GetMapping("/{uuid}/remove/{id}")
    public void deleteById(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid, @PathVariable Long id) {
        String targetUuid = getCartUuid(username, uuid);
        cartService.deleteById(targetUuid, id);
    }

    @GetMapping("/{uuid}/clear")
    public void clearCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid) {
        String targetUuid = getCartUuid(username, uuid);
        cartService.deleteAllFromCart(targetUuid);
    }

    @GetMapping("/{uuid}/change_quantity")
    public void changePrice(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid, @RequestParam Long productId, @RequestParam Integer delta) {
        String targetUuid = getCartUuid(username, uuid);
        cartService.changeQuantity(targetUuid, productId, delta);
    }

    private String getCartUuid(String username, String uuid) {

        if (username != null) {
            return username;
        }
        return uuid;
    }


}
