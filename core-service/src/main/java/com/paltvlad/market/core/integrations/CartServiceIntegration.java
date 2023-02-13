package com.paltvlad.market.core.integrations;

import com.paltvlad.market.api.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
@RequiredArgsConstructor
public class CartServiceIntegration {

    private final WebClient cartServiceWebClient;


    public CartDto getCurrentCart() {

        return cartServiceWebClient.get()
                .uri("/api/v1/cart/")
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();

    }


    public void clearCart() {
        cartServiceWebClient.get()
                .uri("/api/v1/cart/clear")
                .retrieve()
                .toBodilessEntity()
                .block();

    }
}

