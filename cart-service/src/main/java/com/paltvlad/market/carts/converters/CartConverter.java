package com.paltvlad.market.carts.converters;

import com.paltvlad.market.api.CartDto;

import com.paltvlad.market.carts.models.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartConverter {

    private final CartItemConverter cartItemConverter;

    public CartDto modelToDto(Cart cart){

        CartDto cartDto = new CartDto();
        cartDto.setTotalPrice(cartDto.getTotalPrice());
        cartDto.setItems(cart.getItems().stream().map(cartItemConverter::modelToDto).collect(Collectors.toList()));

        return cartDto;
    }
}
