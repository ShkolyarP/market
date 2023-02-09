package com.paltvlad.market.carts.services;

import com.paltvlad.market.api.ProductDto;
import com.paltvlad.market.api.ResourceNotFoundException;
import com.paltvlad.market.carts.integrations.ProductServiceIntegration;
import com.paltvlad.market.carts.models.Cart;
import com.paltvlad.market.carts.models.CartItem;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;

    private Cart tempCart;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }

    public Cart getCurrentCart() {
        return tempCart;
    }

    public void addToCard(Long id) {
        ProductDto productDto = productServiceIntegration.getProductById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));

        tempCart.addToCart(productDto);


    }


    public void changeQuantity(Long id, Integer delta) {

        CartItem item = tempCart.findById(id);
        if (!(item.getQuantity() == 1 && delta == -1)) {
            item.changeQuantity(delta);
        }
        tempCart.recalculate();
    }

    public void deleteById(Long id) {

        tempCart.deleteById(id);
    }

    public void deleteAllFromCart() {
        tempCart.clearCart();
    }
}
