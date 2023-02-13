package com.paltvlad.market.carts.converters;

import com.paltvlad.market.api.CartItemDto;
import com.paltvlad.market.carts.models.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemConverter {
    public CartItemDto modelToDto(CartItem cartItem){
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setPrice(cartItem.getPrice());
        cartItemDto.setPricePerProduct(cartItem.getPricePerProduct());
        cartItemDto.setProductId(cartItem.getProductId());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setProductTitle(cartItem.getProductTitle());
        return cartItemDto;
    }
}
