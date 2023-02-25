package com.paltvlad.market.core.integrations;

import com.paltvlad.market.api.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
@RequiredArgsConstructor
public class CartServiceIntegration {

    private final WebClient cartServiceWebClient;


    public CartDto getCurrentCart(String username) {

        return cartServiceWebClient.get()
                .uri("/api/v1/cart/0")
                .header("username", username)
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();

    }


    public void clearCart(String username) {
        cartServiceWebClient.get()
                .uri("/api/v1/cart/0/clear")
                .header("username", username)
                .retrieve()
                .toBodilessEntity()
                .block();

    }
}

