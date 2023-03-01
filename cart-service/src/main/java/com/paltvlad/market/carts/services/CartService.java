package com.paltvlad.market.carts.services;

import com.paltvlad.market.api.ProductDto;
import com.paltvlad.market.api.ResourceNotFoundException;
import com.paltvlad.market.carts.integrations.ProductServiceIntegration;
import com.paltvlad.market.carts.models.Cart;
import com.paltvlad.market.carts.models.CartItem;

import javax.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    @Value("${cart-service.cart-prefix}")
    private String cartPrefix;
    private Map<String, Cart> carts;


    @PostConstruct
    public void init() {
        carts = new HashMap<>();
    }

    public Cart getCurrentCart(String uuid) {

        String targetUuid = cartPrefix + uuid;

        if (!carts.containsKey(targetUuid)) {
            carts.put(targetUuid, new Cart());
        }
        return carts.get(targetUuid);
    }

    public void addToCard(String uuid, Long id) {
        ProductDto productDto = productServiceIntegration.getProductById(id);

        getCurrentCart(uuid).addToCart(productDto);


    }


    public void changeQuantity(String uuid, Long id, Integer delta) {

        CartItem item = getCurrentCart(uuid).findById(id);
        if (!(item.getQuantity() == 1 && delta == -1)) {
            item.changeQuantity(delta);
        }
        getCurrentCart(uuid).recalculate();
    }

    public void deleteById(String uuid, Long id) {

        getCurrentCart(uuid).deleteById(id);
    }

    public void deleteAllFromCart(String uuid) {
        getCurrentCart(uuid).clearCart();
    }
}
