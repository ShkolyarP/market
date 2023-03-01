package com.paltvlad.market.carts.services;

import com.paltvlad.market.api.ProductDto;
import com.paltvlad.market.carts.integrations.ProductServiceIntegration;
import com.paltvlad.market.carts.models.Cart;
import com.paltvlad.market.carts.models.CartItem;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private final RedisTemplate<String, Object> redisTemplate;
    @Value("${cart-service.cart-prefix}")
    private String cartPrefix;


    private String getTargetUuid(String uuid) {
        return cartPrefix + uuid;
    }

    public Cart getCurrentCart(String uuid) {

        String targetUuid = getTargetUuid(uuid);
        if (Boolean.FALSE.equals(redisTemplate.hasKey(targetUuid))) {
            redisTemplate.opsForValue().set(targetUuid, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(targetUuid);
    }

    public void addToCard(String uuid, Long id) {
        ProductDto productDto = productServiceIntegration.getProductById(id);
        execute(uuid, cart -> cart.addToCart(productDto));
    }


    public void changeQuantity(String uuid, Long id, Integer delta) {

        execute(uuid, cart -> {
            CartItem item = cart.findById(id);
            if (!(item.getQuantity() == 1 && delta == -1)) {
                item.changeQuantity(delta);
            }
            cart.recalculate();
        });

    }

    public void deleteById(String uuid, Long id) {
        execute(uuid, cart -> cart.deleteById(id));
    }

    public void deleteAllFromCart(String uuid) {
        execute(uuid, Cart::clearCart);
    }

    private void execute(String uuid, Consumer<Cart> operation) {
        String targetUuid = getTargetUuid(uuid);
        Cart cart = getCurrentCart(uuid);
        operation.accept(cart);
        redisTemplate.opsForValue().set(targetUuid, cart);
    }

    public void mergeCart(String uuid, String username) {
        Cart cartUuid = getCurrentCart(uuid);
        Cart cartUsername = getCurrentCart(username);
        cartUuid.getItems().stream().forEach(cartUsername::addItem);
        cartUuid.clearCart();

        redisTemplate.opsForValue().set(cartPrefix + uuid, cartUuid);
        redisTemplate.opsForValue().set(cartPrefix + username, cartUsername);
    }
}
