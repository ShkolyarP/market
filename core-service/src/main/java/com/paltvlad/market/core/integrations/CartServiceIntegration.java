package com.paltvlad.market.core.integrations;

import com.paltvlad.market.api.CartDto;
import com.paltvlad.market.api.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {

    private final RestTemplate restTemplate;

    public CartDto getCurrentCart() {

        return restTemplate.getForObject("http://localhost:8190/market-carts/api/v1/cart", CartDto.class);

    }

    public void clearCart(){
       restTemplate.getForObject("http://localhost:8190/market-carts/api/v1/cart/clear", void.class);
    }
}

